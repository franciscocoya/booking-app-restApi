package com.hosting.rest.api.controllers.Accomodation.AccomodationRule;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hosting.rest.api.exceptions.IllegalArguments.IllegalArgumentsCustomException;
import com.hosting.rest.api.models.Accomodation.AccomodationRule.AccomodationAccRuleModel;
import com.hosting.rest.api.models.Accomodation.AccomodationRule.AccomodationRuleModel;
import com.hosting.rest.api.services.Accomodation.AccomodationRule.AccomodationRuleServiceImpl;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author Francisco Coya
 * @version v1.0.3
 * @apiNote Controlador de una norma de alojamiento.
 *
 */
@RestController
@CrossOrigin(origins = { "*" })
@RequestMapping("/accomodations/rules")
@Slf4j
public class AccomodationRuleController {

	@Autowired
	private AccomodationRuleServiceImpl accomodationRuleService;

	@PreAuthorize("hasRole('ROLE_HOST_USER') or hasRole('ROLE_ADMIN_USER')")
	@PostMapping("new")
	public AccomodationRuleModel addNewAccomodationRule(
			@RequestBody final AccomodationRuleModel accomodationRuleToAdd) {
		return accomodationRuleService.addNewAccomodationRule(accomodationRuleToAdd);
	}

	@PreAuthorize("hasRole('ROLE_HOST_USER') or hasRole('ROLE_ADMIN_USER')")
	@PutMapping("{accomodationRuleId}")
	public void updateAccomodationRule(@PathVariable(value = "accomodationRuleId") final String accomodationRuleId,
			@RequestBody final AccomodationRuleModel accomodationRuleToUpdate) {

		try {
			accomodationRuleService.updateAccomodationRule(Integer.parseInt(accomodationRuleId),
					accomodationRuleToUpdate);

		} catch (NumberFormatException nfe) {
			log.error("El id de la norma del alojamiento [ " + accomodationRuleId + " ] no es un número.");
			throw new IllegalArgumentsCustomException(
					"El id de la norma del alojamiento [ " + accomodationRuleId + " ] no es un número.");
		}

	}

	@PreAuthorize("hasRole('ROLE_HOST_USER') or hasRole('ROLE_ADMIN_USER')")
	@DeleteMapping("{accomodationRuleId}")
	public void deleteAccomodationRuleById(
			@PathVariable(value = "accomodationRuleId") final String accomodationRuleId) {
		try {
			accomodationRuleService.deleteAccomodationRule(Integer.parseInt(accomodationRuleId));

		} catch (NumberFormatException nfe) {
			log.error("El id de la norma del alojamiento [ " + accomodationRuleId + " ] no es un número.");
			throw new IllegalArgumentsCustomException(
					"El id de la norma del alojamiento [ " + accomodationRuleId + " ] no es un número.");
		}
	}

	@PreAuthorize("hasRole('ROLE_BASE_USER') or hasRole('ROLE_HOST_USER') or hasRole('ROLE_ADMIN_USER')")
	@GetMapping("{accomodationRuleId}")
	public AccomodationRuleModel findById(@PathVariable(value = "accomodationRuleId") final String accomodationRuleId) {
		AccomodationRuleModel accomodationRuleToReturn = null;

		try {
			accomodationRuleToReturn = accomodationRuleService.findById(Integer.parseInt(accomodationRuleId));

		} catch (NumberFormatException nfe) {
			log.error("El id de la norma del alojamiento [ " + accomodationRuleId + " ] no es un número.");
			throw new IllegalArgumentsCustomException(
					"El id de la norma del alojamiento [ " + accomodationRuleId + " ] no es un número.");
		}

		return accomodationRuleToReturn;
	}

	@PreAuthorize("hasRole('ROLE_BASE_USER') or hasRole('ROLE_HOST_USER') or hasRole('ROLE_ADMIN_USER')")
	@GetMapping("{accomodationRegNumber}/all")
	public List<AccomodationRuleModel> findByAccomodationRegNumber(
			@PathVariable(value = "accomodationRegNumber") String regNumber) {
		return accomodationRuleService.findByAccomodationRegNumber(regNumber);
	}

	@PreAuthorize("hasRole('ROLE_BASE_USER') or hasRole('ROLE_HOST_USER') or hasRole('ROLE_ADMIN_USER')")
	@GetMapping("all")
	public List<AccomodationRuleModel> listAllAvailableAccomodationRules() {
		return accomodationRuleService.findAllRules();
	}

	/**
	 * 
	 * @return
	 */
	@PostMapping("{regNumber}/new")
	public AccomodationAccRuleModel addRuleToAccomodation(@PathVariable(name = "regNumber") final String regNumber,
			@RequestParam(name = "rule") final String accomodationRuleId) {
		AccomodationAccRuleModel accomodationAccRuleToReturn = null;

		try {
			accomodationAccRuleToReturn = accomodationRuleService
					.addNewAccomodationRuleToExistingAccomodation(Integer.parseInt(accomodationRuleId), regNumber);

		} catch (NumberFormatException nfe) {
			log.error("El id de la norma no es un número");
			throw new IllegalArgumentsCustomException("El id de la norma no es un número");
		}

		return accomodationAccRuleToReturn;
	}

	@DeleteMapping("{regNumber}/{accomodationRuleId}")
	public void deleteAccomodationRuleFromAccomodation(@PathVariable(name = "regNumber") final String regNumber,
			@PathVariable(name = "rule") final String accomodationRuleId) {
		try {
			accomodationRuleService.deleteAccomodationRuleFromAccomodation(Integer.parseInt(accomodationRuleId),
					regNumber);
		} catch (NumberFormatException nfe) {
			log.error("El id de la norma no es un número");
			throw new IllegalArgumentsCustomException("El id de la norma no es un número");
		}
	}

}
