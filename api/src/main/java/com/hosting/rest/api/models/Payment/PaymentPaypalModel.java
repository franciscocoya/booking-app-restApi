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
@DiscriminatorValue("P")
@Table( name = "PAYMENT_PAYPAL" )
public class PaymentPaypalModel extends PaymentModel{

    @Column(name = "ACCOUNT_EMAIL")
    private String accountEmail;
}
