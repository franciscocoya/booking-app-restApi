package com.hosting.rest.api.services.Payment;

import java.util.List;

import com.hosting.rest.api.models.Payment.PaymentModel;

public interface IPaymentService {

	public PaymentModel addNewPayment();

	public void removePaymentById(final Integer paymentId);

	public List<PaymentModel> findAllPayments();

	public PaymentModel findByBookingId(final Integer bookingId);
	
	public Integer getLastPaymentId();
}
