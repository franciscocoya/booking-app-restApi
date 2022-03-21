package com.hosting.rest.api.models.Payment;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table( name = "PAYMENT_CREDIT_CARD" )
public class PaymentCreditCardModel extends PaymentModel{

    @Column(name = "CARD_NUMBER")
    private String cardNumber;
}
