package com.hosting.rest.api.repositories.Payment;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hosting.rest.api.models.Payment.PaymentModel;

public interface IPaymentRepository extends JpaRepository<PaymentModel, Integer> {
	
}
