package com.hosting.rest.api.services.Payment.PaymentCreditCard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hosting.rest.api.models.Payment.PaymentCreditCardModel;
import com.hosting.rest.api.repositories.Payment.IPaymentCreditCardRepository;

import static com.hosting.rest.api.Utils.ServiceParamValidator.validateParam;
import static com.hosting.rest.api.Utils.ServiceParamValidator.validateParamNotFound;

import static com.hosting.rest.api.Utils.AppUtils.isNotNull;

@Service
public class PaymentCreditCardServiceImpl implements IPaymentCreditCardService {

	@Autowired
	private IPaymentCreditCardRepository paymentCreditCardRepo;

	/**
	 * Crea un nuevo pago con tarjeta de crédito.
	 * 
	 * @param paymentCreditCardToAdd
	 * 
	 * @return
	 */
	@Override
	public PaymentCreditCardModel addNewPaymentCreditCard(final PaymentCreditCardModel paymentCreditCardToAdd) {

		// Validar pago a añadir
		validateParam(isNotNull(paymentCreditCardToAdd),
				"Alguno de los valores introducidos para la tarjeta no es válido.");

		// Comprobar si existe el pago con tarjeta a añadir
		validateParamNotFound(!paymentCreditCardRepo.existsById(paymentCreditCardToAdd.getIdPayment()),
				"Ya existe el método de pago a añadir.");

		return paymentCreditCardRepo.save(paymentCreditCardToAdd);
	}

}
