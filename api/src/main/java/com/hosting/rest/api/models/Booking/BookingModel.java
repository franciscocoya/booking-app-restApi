package com.hosting.rest.api.models.Booking;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Formula;
import org.springframework.data.annotation.CreatedDate;

import com.hosting.rest.api.models.Accomodation.AccomodationModel;
import com.hosting.rest.api.models.Payment.PaymentModel;
import com.hosting.rest.api.models.User.UserModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "BOOKING")
public class BookingModel implements Serializable{

	private static final long serialVersionUID = -5582102291546732206L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Integer id;

	@Column(name = "CHECK_IN")
	private LocalDateTime checkIn;

	@Column(name = "CHECK_OUT")
	private LocalDateTime checkOut;

	@Column(name = "GUESTS")
	private Integer numOfGuests;

	@Column(name = "AMOUNT")
	private BigDecimal amount;

	@Column(name = "DISCCOUNT")
	private BigDecimal disccount;

	@Formula("amount * 0.10")
	@Column(name = "SERVICE_FEE")
	private BigDecimal serviceFee;

	@Formula("amount + service_fee - disccount")
	@Column(name = "TOTAL")
	private BigDecimal total;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "BOOKING_STATUS")
	private BookingStatus bookingStatus;

	@ManyToOne
	@JoinColumn(name = "ID_USER")
	private UserModel idUser;

	@ManyToOne
	@JoinColumn(name = "ID_ACCOMODATION")
	private AccomodationModel idAccomodation;

	@OneToOne
	@JoinColumn(name = "PAYMENT_ID")
	private PaymentModel idPayment;

	@Column(name = "CREATED_AT")
	@CreatedDate
	private LocalDateTime createdAt;
}
