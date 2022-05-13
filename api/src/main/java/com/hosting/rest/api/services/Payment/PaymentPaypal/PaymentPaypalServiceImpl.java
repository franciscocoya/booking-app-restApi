package com.hosting.rest.api.services.Payment.PaymentPaypal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hosting.rest.api.models.Payment.PaymentPaypalModel;
import com.hosting.rest.api.repositories.Payment.IPaymentPaypalRepository;

import static com.hosting.rest.api.Utils.ServiceParamValidator.validateParam;
import static com.hosting.rest.api.Utils.ServiceParamValidator.validateParamNotFound;

import static com.hosting.rest.api.Utils.AppUtils.isNotNull;

@Service
public class PaymentPaypalServiceImpl implements IPaymentPaypalService {

	@Autowired
	private IPaymentPaypalRepository paymentPaypalRepo;

	@Override
	public PaymentPaypalModel addNewPaymentPayPal(final PaymentPaypalModel paymentPaypalToAdd) {
		// Validar pago a añadir
		validateParam(isNotNull(paymentPaypalToAdd),
				"Alguno de los valores introducidos para la cuenta de PayPal no es válido.");

		// Comprobar si existe el pago con paypal a añadir
		validateParamNotFound(!paymentPaypalRepo.existsById(paymentPaypalToAdd.getIdPayment()),
				"Ya existe el método de pago a añadir.");

		return paymentPaypalRepo.save(paymentPaypalToAdd);
	}

}
