package com.hosting.rest.api.models.Payment;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper=false)
@Getter
@Setter
@PrimaryKeyJoinColumn(name = "ID_PAYMENT")
@DiscriminatorValue("C")
@Table( name = "PAYMENT_CREDIT_CARD" )
public class PaymentCreditCardModel extends PaymentModel{

    @Column(name = "CARD_NUMBER")
    private String cardNumber;
}
