package com.hosting.rest.api.models.Accomodation.AccomodationRule;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Embeddable
public class AccomodationAccRuleId implements Serializable {

	private static final long serialVersionUID = -18659259070359999L;

	@Column(name = "ID_ACC")
	private String idAccomodation;

	@Column(name = "ID_ACC_RULE")
	private Integer idAccomodationRule;
}
