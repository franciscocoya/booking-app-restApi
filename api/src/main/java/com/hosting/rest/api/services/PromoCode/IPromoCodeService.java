package com.hosting.rest.api.services.PromoCode;

import java.util.List;

import com.hosting.rest.api.models.PromoCode.PromoCodeModel;

public interface IPromoCodeService{
	
	// TODO: Crear un nuevo código promocional.
	PromoCodeModel addNewPromoCode(PromoCodeModel promoCodeModel);

	// TODO: Listado de todos los codigos promocionales existentes - ADMIN.
	List<PromoCodeModel> listAllPromoCodes();
	
	// TODO: Obtener un codigo promocional.
	PromoCodeModel getPromoCodeById(Integer promoCodeId);
	
	// TODO: Listado de los códigos promocionales creados por un usuario.
	List<PromoCodeModel> listAllPromoCodeFromUser(Integer userId);

	// TODO: Eliminar un código promocional
	void removePromoCodeById(Integer promoCodeId);
}
