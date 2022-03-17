package com.hosting.rest.api.models.Payment;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table( name = "PAYMENT_CREDIT_CARD" )
@Data
public class PaymentCreditCardModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PAYMENT")
    private Integer idPayment;

    private String cardNumber;
}
