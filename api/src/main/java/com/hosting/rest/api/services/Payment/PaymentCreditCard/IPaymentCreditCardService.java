package com.hosting.rest.api.services.Payment.PaymentCreditCard;

import com.hosting.rest.api.models.Payment.PaymentCreditCardModel;

public interface IPaymentCreditCardService {
	
	public PaymentCreditCardModel addNewPaymentCreditCard(final PaymentCreditCardModel paymentCreditCardToAdd);
}
