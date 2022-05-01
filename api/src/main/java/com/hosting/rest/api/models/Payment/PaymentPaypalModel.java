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
@DiscriminatorValue("P")
@PrimaryKeyJoinColumn(name = "ID_PAYMENT")
@Table( name = "PAYMENT_PAYPAL" )
public class PaymentPaypalModel extends PaymentModel{

    private static final long serialVersionUID = 6553559064683143104L;
    
	@Column(name = "ACCOUNT_EMAIL")
    private String accountEmail;
}
