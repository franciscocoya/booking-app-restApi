package com.hosting.rest.api.models.Payment;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "PAYMENT")
public class PaymentModel implements Serializable{

	private static final long serialVersionUID = 8006984459012648590L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Integer idPayment;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "PAYMENT_STATUS")
	private PaymentStatus paymentStatus;

	@Column(name = "CREATED_AT")
	@CreatedDate
	private LocalDateTime createdAt;
}
