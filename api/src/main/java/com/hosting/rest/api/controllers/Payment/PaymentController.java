package com.hosting.rest.api.controllers.Payment;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hosting.rest.api.exceptions.IllegalArguments.IllegalArgumentsCustomException;
import com.hosting.rest.api.models.Payment.PaymentModel;
import com.hosting.rest.api.services.Payment.PaymentServiceImpl;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author Francisco Coya
 * @version v1.0.3
 * @apiNote Controlador de los pagos realizados en la aplicación.
 *
 */
@RestController
@CrossOrigin(origins = { "*" })
@RequestMapping("/payments")
@Slf4j
public class PaymentController {

	@Autowired
	private PaymentServiceImpl paymentService;

	@PreAuthorize("hasRole('ROLE_HOST_USER') or hasRole('ROLE_ADMIN_USER')")
	@PostMapping("new")
	public PaymentModel addNewPaymentMethod(@Valid @RequestBody final PaymentModel paymentToAdd) {
		return paymentService.addNewPayment(paymentToAdd);
	}
	
	@PreAuthorize("hasRole('ROLE_HOST_USER') or hasRole('ROLE_ADMIN_USER')")
	@GetMapping("lastPaymentId")
	public Integer getLastPaymentId() {
		return paymentService.getLastPaymentId();
	}

	@PreAuthorize("hasRole('ROLE_HOST_USER') or hasRole('ROLE_ADMIN_USER')")
	@DeleteMapping("{paymentId}")
	public void removePaymentMethod(@PathVariable(value = "paymentId") final String paymentId) {

		try {
			paymentService.removePaymentById(Integer.parseInt(paymentId));

		} catch (NumberFormatException nfe) {
			log.error("El id del método de pago [ " + paymentId + " ] no es un número.");
			throw new IllegalArgumentsCustomException(
					"El id del método de pago [ " + paymentId + " ] no es un número.");
		}
	}

	@PreAuthorize("hasRole('ROLE_BASE_USER') or hasRole('ROLE_HOST_USER') or hasRole('ROLE_ADMIN_USER')")
	@GetMapping("all")
	public List<PaymentModel> listAllPaymentMethods() {
		return paymentService.findAllPayments();
	}

	@PreAuthorize("hasRole('ROLE_HOST_USER') or hasRole('ROLE_ADMIN_USER')")
	@GetMapping("{bookingId}")
	public PaymentModel getPaymentMethodFromBooking(@PathVariable(value = "bookingId") final String bookingId) {
		PaymentModel paymentToReturn = null;

		try {
			paymentToReturn = paymentService.findByBookingId(Integer.parseInt(bookingId));

		} catch (NumberFormatException nfe) {
			log.error("El id de la reserva [ " + bookingId + " ] no es un número.");
			throw new IllegalArgumentsCustomException("El id de la reserva [ " + bookingId + " ] no es un número.");
		}

		return paymentToReturn;
	}
}
