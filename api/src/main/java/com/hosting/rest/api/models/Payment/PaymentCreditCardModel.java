package com.hosting.rest.api.models.Payment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@PrimaryKeyJoinColumn(name = "ID_PAYMENT")
@Table( name = "PAYMENT_CREDIT_CARD" )
public class PaymentCreditCardModel extends PaymentModel{

    @Column(name = "CARD_NUMBER")
    private String cardNumber;
}
