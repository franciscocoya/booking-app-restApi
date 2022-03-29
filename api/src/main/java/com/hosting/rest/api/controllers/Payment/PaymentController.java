package com.hosting.rest.api.controllers.Payment;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hosting.rest.api.models.Payment.PaymentModel;
import com.hosting.rest.api.services.Payment.PaymentServiceImpl;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/payments")
public class PaymentController {

	@Autowired
	private PaymentServiceImpl paymentService;

	@PostMapping("/new")
	public PaymentModel addNewPaymentMethod(@RequestBody final PaymentModel paymentModel) {
		return paymentService.addNewPayment(paymentModel);
	}

	@PutMapping("{paymentId}")
	public PaymentModel updatePaymentModel(@PathVariable(value = "paymentId") final PaymentModel paymentModel) {
		return paymentService.updatePaymentById(paymentModel);
	}

	@DeleteMapping("{paymentId}")
	public void removePaymentMethod(@PathVariable(value = "paymentId") final Integer paymentId) {
		paymentService.removePaymentById(paymentId);
	}

	@GetMapping("all")
	public List<PaymentModel> listAllPaymentMethods() {
		return paymentService.listAllPayments();
	}

	@GetMapping("{bookingId}")
	public PaymentModel getPaymentMethodFromBooking(@PathVariable(value = "bookingId") final Integer bookingId) {
		return paymentService.getPaymentFromBooking(bookingId);
	}
}
