package com.hosting.rest.api.services.Payment;

import java.util.List;

import com.hosting.rest.api.models.Payment.PaymentModel;

public interface IPaymentService {

	PaymentModel addNewPayment(PaymentModel paymentModel);

	PaymentModel updatePaymentById(PaymentModel paymentModel);

	void removePaymentById(Integer paymentId);

	List<PaymentModel> listAllPayments();

	PaymentModel getPaymentFromBooking(Integer bookingId);
}
