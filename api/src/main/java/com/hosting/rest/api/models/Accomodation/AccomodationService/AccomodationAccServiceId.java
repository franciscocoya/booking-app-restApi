package com.hosting.rest.api.models.Accomodation.AccomodationService;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * 
 * @author Francisco Coya
 * @version v1.0.1
 *
 */
@Embeddable
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class AccomodationAccServiceId implements Serializable {

	private static final long serialVersionUID = -4427770164602317764L;

	@Column(name = "ID_ACC")
	private String idAccomodation;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_ACC_SERVICE")
	private AccomodationServiceModel idAccomodationService;
}
