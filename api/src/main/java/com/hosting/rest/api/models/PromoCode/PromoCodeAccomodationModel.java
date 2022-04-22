package com.hosting.rest.api.models.PromoCode;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * 
 * @author Francisco Coya
 * @version v1.0.0
 * @apiNote Modelo para la relación promo code - accomodation.
 *
 */
@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ACCOMODATION_PROMO_CODE")
public class PromoCodeAccomodationModel implements Serializable{
	
	private static final long serialVersionUID = 4197130669350221810L;
	
	@EmbeddedId
	private PromoCodeAccomodationId promoCodeAccomodationId;
}
