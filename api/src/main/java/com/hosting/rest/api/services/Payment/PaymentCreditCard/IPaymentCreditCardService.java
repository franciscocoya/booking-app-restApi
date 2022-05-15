package com.hosting.rest.api.services.Payment.PaymentCreditCard;

import com.hosting.rest.api.models.Payment.PaymentCreditCardModel;

public interface IPaymentCreditCardService {

	public static final int CREDIT_CARD_NUMBER_MIN_DIGITS = 12;
	
	public PaymentCreditCardModel addNewPaymentCreditCard(final PaymentCreditCardModel paymentCreditCardToAdd);
}
