package com.hosting.rest.api.models.Accomodation.AccomodationService;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * 
 * @author Francisco Coya
 *
 */
@AllArgsConstructor
@Data
@Embeddable
public class AccomodationAccServiceId implements Serializable{

	@Column(name = "ID_ACC")
	private String idAccomodation;

	@Column(name = "ID_ACC_SERVICE")
	private int idAccomodationService;
}
