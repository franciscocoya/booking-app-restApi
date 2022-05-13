package com.hosting.rest.api.controllers.Payment;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hosting.rest.api.models.Payment.PaymentCreditCardModel;
import com.hosting.rest.api.services.Payment.PaymentCreditCard.PaymentCreditCardServiceImpl;

/**
 * 
 * @author Francisco Coya
 * @version v1.0.0
 * @apiNote Controlador de los pagos realizados con tarjeta.
 *
 */
@RestController
@CrossOrigin(origins = { "*" })
@RequestMapping("/payments/credit-card")
public class PaymentCreditCardController {

	@Autowired
	private PaymentCreditCardServiceImpl paymentCreditCardService;

	@PreAuthorize("hasRole('ROLE_BASE_USER') or hasRole('ROLE_HOST_USER') or hasRole('ROLE_ADMIN_USER')")
	@PostMapping("new")
	public PaymentCreditCardModel addNewPaymentCreditCard(
			@Valid @RequestBody final PaymentCreditCardModel paymentCreditCardToAdd) {
		return paymentCreditCardService.addNewPaymentCreditCard(paymentCreditCardToAdd);
	}
}
