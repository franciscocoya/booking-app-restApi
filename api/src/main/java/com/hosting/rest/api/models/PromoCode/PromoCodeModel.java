package com.hosting.rest.api.models.PromoCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;

import com.hosting.rest.api.models.User.UserHostModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Table(name = "PROMO_CODE")
public class PromoCodeModel implements Serializable{

	private static final long serialVersionUID = 5155189233228575480L;

	@Id
	@Column(name = "SERIAL_NUM")
	private String serial_num;

	@Column(name = "AMOUNT_PERCENTAGE")
	private BigDecimal amountPercentange;

	@Column(name = "DATE_START")
	private LocalDateTime dateStart;

	@Column(name = "DATE_END")
	private LocalDateTime dateEnd;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_USER")
	private UserHostModel idUser;

	@Column(name = "CREATED_AT")
	@CreatedDate
	private LocalDateTime createdAt;
}
