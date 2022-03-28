package com.hosting.rest.api.models.Accomodation.AccomodationService;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;

/**
 * 
 * @author Francisco Coya
 *
 */
@AllArgsConstructor
@Embeddable
public class AccomodationAccServiceId implements Serializable{

	private static final long serialVersionUID = 1L;

	@Column(name = "ID_ACC")
	private String idAccomodation;

	@Column(name = "ID_ACC_SERVICE")
	private int idAccomodationService;
}
