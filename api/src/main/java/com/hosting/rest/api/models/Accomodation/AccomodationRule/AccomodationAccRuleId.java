package com.hosting.rest.api.models.Accomodation.AccomodationRule;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Embeddable
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class AccomodationAccRuleId implements Serializable {

	private static final long serialVersionUID = -18659259070359999L;

	@Column(name = "ID_ACC")
	private String idAccomodation;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_ACC_RULE")
	private AccomodationRuleModel idAccomodationRule;
}
