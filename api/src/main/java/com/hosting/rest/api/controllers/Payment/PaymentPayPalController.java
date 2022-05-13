package com.hosting.rest.api.controllers.Payment;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hosting.rest.api.models.Payment.PaymentPaypalModel;
import com.hosting.rest.api.services.Payment.PaymentPaypal.PaymentPaypalServiceImpl;

/**
 * 
 * @author Francisco Coya
 * @version v1.0.0
 * @apiNote Controlador de los pagos realizados con PayPal.
 *
 */
@RestController
@CrossOrigin(origins = { "*" })
@RequestMapping("/payments/paypal")
public class PaymentPayPalController {
	
	@Autowired
	private PaymentPaypalServiceImpl paymentPaypalService;

	@PreAuthorize("hasRole('ROLE_BASE_USER') or hasRole('ROLE_HOST_USER') or hasRole('ROLE_ADMIN_USER')")
	@PostMapping("new")
	public PaymentPaypalModel addNewPaymentCreditCard(
			@Valid @RequestBody final PaymentPaypalModel paymentPaypalToAdd) {
		return paymentPaypalService.addNewPaymentPayPal(paymentPaypalToAdd);
	}
}
