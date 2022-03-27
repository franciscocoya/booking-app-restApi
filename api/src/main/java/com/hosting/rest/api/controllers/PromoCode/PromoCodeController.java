package com.hosting.rest.api.controllers.PromoCode;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hosting.rest.api.models.PromoCode.PromoCodeModel;
import com.hosting.rest.api.services.PromoCode.PromoCodeServiceImpl;

@RestController
@RequestMapping(value = "/codes")
public class PromoCodeController {
	
	@Autowired
	private PromoCodeServiceImpl promoCodeService;
	
	// TODO: Crear codigo promocional.
	@PostMapping(name = "new")
	public PromoCodeModel addNewPromoCode(PromoCodeModel promoCodeModel) {
		return promoCodeService.addNewPromoCode(promoCodeModel);
	}
	
	// TODO: Eliminar un codigo promocional.
	@DeleteMapping(name = "{promoId}")
	public void deletePromoCode(int serialNumber) {
		promoCodeService.removePromoCodeById(serialNumber);
	}
	
	// TODO: Listado de todos los códigos promocionales.
	@GetMapping(name = "/all")
	public List<PromoCodeModel> listAllPromoCodes(){
		return promoCodeService.listAllPromoCodes();
	}
	
	// TODO: Listado de los codigos promocionales creados por un usuario.
	/*@GetMapping(name = "/{userId}/all")
	public List<PromoCodeModel> listAllPromoCodesFromUser(Integer userId){
		return promoCodeService.listAllPromoCodeFromUser(userId);
	}*/
}
