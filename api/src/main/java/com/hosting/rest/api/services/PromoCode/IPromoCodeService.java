package com.hosting.rest.api.services.PromoCode;

import java.util.List;

import com.hosting.rest.api.models.PromoCode.PromoCodeModel;

public interface IPromoCodeService{
	
	PromoCodeModel addNewPromoCode(PromoCodeModel promoCodeModel);

	List<PromoCodeModel> listAllPromoCodes();
	
	PromoCodeModel getPromoCodeById(String promoCodeId);
	
	List<PromoCodeModel> listAllPromoCodeFromUser(String userId);

	void removePromoCodeById(String promoCodeId);
}
