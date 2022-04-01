package com.hosting.rest.api.services.Payment;

import java.util.List;

import com.hosting.rest.api.models.Payment.PaymentModel;

public interface IPaymentService {

	public PaymentModel addNewPayment(final PaymentModel paymentModel);

	public PaymentModel updatePaymentById(final Integer paymentId, final PaymentModel paymentModel);

	public void removePaymentById(final Integer paymentId);

	public List<PaymentModel> findAllPayments();

	public PaymentModel findByBookingId(final Integer bookingId);
}
