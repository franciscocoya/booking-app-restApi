package com.hosting.rest.api.models.PromoCode;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hosting.rest.api.models.Accomodation.AccomodationModel;
import com.hosting.rest.api.models.User.UserHostModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "PROMO_CODE")
public class PromoCodeModel {

	@Id
	@Column(name = "SERIAL_NUM")
	private String serial_num;

	@Column(name = "AMOUNT_PERCENTAGE")
	private BigDecimal amountPercentange;

	@Column(name = "DATE_START")
	private LocalDateTime dateStart;

	@Column(name = "DATE_END")
	private LocalDateTime dateEnd;

	@ManyToOne
	@JoinColumn(name = "ID_ACC")
	private AccomodationModel idAcc;

	@ManyToOne
	@JoinColumn(name = "ID_USER")
	private UserHostModel idUser;

	@Column(name = "CREATED_AT")
	@JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss")
	@CreatedDate
	private LocalDateTime createdAt;
}
