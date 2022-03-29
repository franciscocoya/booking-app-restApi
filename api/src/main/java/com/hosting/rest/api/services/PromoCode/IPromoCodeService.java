package com.hosting.rest.api.services.PromoCode;

import java.util.List;

import com.hosting.rest.api.models.PromoCode.PromoCodeModel;

public interface IPromoCodeService{
	
	PromoCodeModel addNewPromoCode(final PromoCodeModel promoCodeModel);

	List<PromoCodeModel> listAllPromoCodes();
	
	PromoCodeModel getPromoCodeById(final String promoCodeId);
	
	List<PromoCodeModel> findAllPromoCodeFromUser(final String userId);

	void removePromoCodeById(final String promoCodeId);
}
