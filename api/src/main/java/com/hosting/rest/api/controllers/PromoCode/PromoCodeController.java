package com.hosting.rest.api.controllers.PromoCode;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hosting.rest.api.models.PromoCode.PromoCodeModel;
import com.hosting.rest.api.services.PromoCode.PromoCodeServiceImpl;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping(value = "/codes")
public class PromoCodeController {
	
	@Autowired
	private PromoCodeServiceImpl promoCodeService;
	
	// TODO: Crear codigo promocional.
	@PostMapping("new")
	public PromoCodeModel addNewPromoCode(@RequestBody PromoCodeModel promoCodeModel) {
		return promoCodeService.addNewPromoCode(promoCodeModel);
	}
	
	@DeleteMapping("{promoId}")
	public void deletePromoCode(@PathVariable(name = "promoId") String serialNumber) {
		promoCodeService.removePromoCodeById(serialNumber);
	}
	
	@GetMapping("{promoId}")
	public PromoCodeModel getPromoCodeById(@PathVariable(name = "promoId") String promoCodeId) {
		return promoCodeService.getPromoCodeById(promoCodeId);
	}
	
	@GetMapping("all")
	public List<PromoCodeModel> listAllPromoCodes(){
		return promoCodeService.listAllPromoCodes();
	}
	
	// TODO: Listado de los codigos promocionales creados por un usuario.
	/*@GetMapping(name = "/{userId}/all")
	public List<PromoCodeModel> listAllPromoCodesFromUser(Integer userId){
		return promoCodeService.listAllPromoCodeFromUser(userId);
	}*/
}
