package com.hosting.rest.api.models.PromoCode;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Embeddable
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class PromoCodeAccomodationId implements Serializable{

	private static final long serialVersionUID = -7989398386019714649L;
	
	@Column(name = "ID_ACC")
	private String idAccomodation;
	
	@ManyToOne
	@JoinColumn(name = "ID_PROMO_CODE")
	private PromoCodeModel idPromoCode;

}
