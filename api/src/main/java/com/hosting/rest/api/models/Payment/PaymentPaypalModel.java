package com.hosting.rest.api.models.Payment;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table( name = "PAYMENT_PAYPAL" )
@Data
public class PaymentPaypalModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PAYMENT")
    private Integer idPayment;

    @Column(name = "ACCOUNT_EMAIL")
    private String accountEmail;
}
