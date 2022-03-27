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

@RestController
@RequestMapping(value = "/payments")
public class PaymentController {

	@Autowired
	private PaymentServiceImpl paymentService;

	// TODO: Añadir método de pago.
	@PostMapping(name = "/new")
	public PaymentModel addNewPaymentMethod(PaymentModel paymentModel) {
		return paymentService.addNewPayment(paymentModel);
	}

	// TODO: Actualizar un método de pago.
	@PutMapping(name = "/update/{paymentId}")
	public PaymentModel updatePaymentModel(@PathVariable(name = "paymentId") PaymentModel paymentModel) {
		return paymentService.updatePaymentById(paymentModel);
	}

	// TODO: Eliminar un método de pago.
	@DeleteMapping(name = "/delete/{paymentId}")
	public void removePaymentMethod(@PathVariable(name = "paymentId") Integer paymentId) {
		paymentService.removePaymentById(paymentId);
	}

	// TODO: Listar todos los métodos de pago disponibles.
	@GetMapping(name = "/all")
	public List<PaymentModel> listAllPaymentMethods() {
		return paymentService.listAllPayments();
	}

	// TODO: Obtener el método de pago de una reserva realizada.
	/*@GetMapping(name = "{bookingId}")
	public PaymentModel getPaymentMethodFromBooking(@PathVariable(name = "bookingId") Integer bookingId) {
		return paymentService.getPaymentFromBooking(bookingId);
	}*/
}
