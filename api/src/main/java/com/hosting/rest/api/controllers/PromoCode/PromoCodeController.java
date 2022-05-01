package com.hosting.rest.api.controllers.PromoCode;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hosting.rest.api.exceptions.IllegalArguments.IllegalArgumentsCustomException;
import com.hosting.rest.api.models.PromoCode.PromoCodeModel;
import com.hosting.rest.api.services.PromoCode.PromoCodeServiceImpl;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author Francisco Coya
 * @version v1.0.3
 * @apiNote Controlador para los códigos promocionales.
 *
 */
@RestController
@RequestMapping(value = "/codes")
@Slf4j
public class PromoCodeController {

	@Autowired
	private PromoCodeServiceImpl promoCodeService;

	@PreAuthorize("hasRole('ROLE_HOST_USER') or hasRole('ROLE_ADMIN_USER')")
	@PostMapping("new")
	public PromoCodeModel addNewPromoCode(@RequestBody final PromoCodeModel promoCodeModel) {
		return promoCodeService.addNewPromoCode(promoCodeModel);
	}

	@PreAuthorize("hasRole('ROLE_HOST_USER') or hasRole('ROLE_ADMIN_USER')")
	@DeleteMapping("{promoId}")
	public void deletePromoCode(@PathVariable(name = "promoId") final String serialNumber) {
		promoCodeService.removePromoCodeById(serialNumber);
	}

	@PreAuthorize("hasRole('ROLE_HOST_USER') or hasRole('ROLE_ADMIN_USER')")
	@PatchMapping("{promoId}")
	public PromoCodeModel updatePromoCode(@PathVariable(name = "promoId") final String promoCodeId,
			@RequestParam(value = "percentage") final String newPromoCodeAmountPercentage) {
		PromoCodeModel updatedPromoCodeToReturn = null;

		try {
			updatedPromoCodeToReturn = promoCodeService.updatePromoCode(promoCodeId,
					new BigDecimal(newPromoCodeAmountPercentage));

		} catch (NumberFormatException nfe) {
			log.error("El id del código promocional no es un número.");
			throw new IllegalArgumentsCustomException("El id del código promocional no es un número.");
		}

		return updatedPromoCodeToReturn;
	}

	@PreAuthorize("hasRole('ROLE_HOST_USER') or hasRole('ROLE_ADMIN_USER')")
	@GetMapping("{promoId}")
	public PromoCodeModel getPromoCodeById(@PathVariable(name = "promoId") final String promoCodeId) {
		return promoCodeService.getPromoCodeById(promoCodeId);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN_USER')")
	@GetMapping("all")
	public List<PromoCodeModel> listAllPromoCodes() {
		return promoCodeService.findAllPromoCodes();
	}

	@PreAuthorize("hasRole('ROLE_ADMIN_USER')")
	@GetMapping("{userId}/all")
	public List<PromoCodeModel> findAllPromoCodesFromUser(@PathVariable(name = "userId") final String userId) {
		List<PromoCodeModel> codes = null;

		try {
			codes = promoCodeService.findByUser(Integer.parseInt(userId));

		} catch (NumberFormatException nfe) {
			log.error("El id del usuario a listar los códigos promocionales creados no es un número.");
			throw new IllegalArgumentsCustomException(
					"El id del usuario a listar los códigos promocionales creados no es un número.");
		}

		return codes;
	}

	@PreAuthorize("hasRole('ROLE_ADMIN_USER')")
	@GetMapping("accomodations/{regNumber}/all")
	public List<PromoCodeModel> findAllPromoCodesFromAccomodation(
			@PathVariable(name = "regNumber") final String accomodationRegisterNumber) {
		return promoCodeService.findByAccomodation(accomodationRegisterNumber);
	}
}
