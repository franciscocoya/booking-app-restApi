package com.hosting.rest.api.models.Payment;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table( name = "PAYMENT_PAYPAL" )
@Data
public class PaymentPaypalModel {

    @Column(name = "ACCOUNT_EMAIL")
    private String accountEmail;
}
