package com.hosting.rest.api.models.Payment;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@PrimaryKeyJoinColumn(name = "ID_PAYMENT")
@Table( name = "PAYMENT_PAYPAL" )
public class PaymentPaypalModel extends PaymentModel{

    @Column(name = "ACCOUNT_EMAIL")
    private String accountEmail;
}
