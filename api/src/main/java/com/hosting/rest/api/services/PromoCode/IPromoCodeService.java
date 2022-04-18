package com.hosting.rest.api.services.PromoCode;

import java.math.BigDecimal;
import java.util.List;

import com.hosting.rest.api.models.PromoCode.PromoCodeModel;

public interface IPromoCodeService {

	public PromoCodeModel addNewPromoCode(final PromoCodeModel promoCodeModel);

	public PromoCodeModel updatePromoCode(final String promoCodeId, final BigDecimal newPromoCodeAmountPercentage);

	public void removePromoCodeById(final String promoCodeId);

	public PromoCodeModel getPromoCodeById(final String promoCodeId);

	public List<PromoCodeModel> findByUser(final Integer userId);

	public List<PromoCodeModel> findByAccomodation(final String accomodationRegNumber);

	public List<PromoCodeModel> findAllPromoCodes();

}
