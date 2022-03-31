package com.hosting.rest.api.models.Payment;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@PrimaryKeyJoinColumn(name = "ID_PAYMENT")
@Table( name = "PAYMENT_CREDIT_CARD" )
@DiscriminatorValue("C")
public class PaymentCreditCardModel extends PaymentModel{

    @Column(name = "CARD_NUMBER")
    private String cardNumber;
}
