package com.hosting.rest.api.models.Booking;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hosting.rest.api.models.Accomodation.AccomodationModel;
import com.hosting.rest.api.models.User.UserModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "BOOKING")
public class BookingModel {

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

	@OneToOne
	@JoinColumn(name = "BILL_NUM")
	private BookingBillModel billNumber;

	@ManyToOne
	@JoinColumn(name = "ID_USER")
	private UserModel idUser;

	@OneToOne
	@JoinColumn(name = "ID_ACCOMODATION")
	private AccomodationModel idAccomodation;

	@Column(name = "CREATED_AT")
	@JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss")
	@CreatedDate
	private LocalDateTime createdAt;
}
