package com.hosting.rest.api.services.Payment;

import java.util.List;

import com.hosting.rest.api.models.Payment.PaymentModel;

public interface IPaymentService {

	// TODO: Añadir método de pago.
	PaymentModel addNewPayment(PaymentModel paymentModel);

	// TODO: Actualizar un método de pago.
	PaymentModel updatePaymentById(PaymentModel paymentModel);

	// TODO: Eliminar un método de pago.
	void removePaymentById(Integer paymentId);

	// TODO: Listar todos los métodos de pago disponibles.
	List<PaymentModel> listAllPaymentMethods();

	// TODO: Obtener el método de pago de una reserva realizada.
	PaymentModel getPaymentFromBooking(Integer bookingId);
}
