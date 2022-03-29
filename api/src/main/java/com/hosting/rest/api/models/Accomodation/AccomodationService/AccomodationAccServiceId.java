package com.hosting.rest.api.models.Accomodation.AccomodationService;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @author Francisco Coya
 * @version v1.0.0
 *
 */
@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccomodationAccServiceId implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "ID_ACC")
	private String idAccomodation;

	@Column(name = "ID_ACC_SERVICE")
	private Integer idAccomodationService;
}
