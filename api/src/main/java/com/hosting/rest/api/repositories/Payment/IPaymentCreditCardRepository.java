package com.hosting.rest.api.repositories.Payment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hosting.rest.api.models.Payment.PaymentCreditCardModel;

@Repository
public interface IPaymentCreditCardRepository extends JpaRepository<PaymentCreditCardModel, Integer>{

}
