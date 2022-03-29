package com.hosting.rest.api.services.PromoCode;

import java.util.List;

import com.hosting.rest.api.models.PromoCode.PromoCodeModel;

public interface IPromoCodeService{
	
	public PromoCodeModel addNewPromoCode(final PromoCodeModel promoCodeModel);

	public List<PromoCodeModel> findAllPromoCodes();
	
	public PromoCodeModel getPromoCodeById(final String promoCodeId);
	
	public List<PromoCodeModel> findByUser(final Integer userId);
	
	public List<PromoCodeModel> findByAccomodation(final String accomodationRegNumber);

	public void removePromoCodeById(final String promoCodeId);
}
