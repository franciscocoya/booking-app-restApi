package com.hosting.rest.api.models.Accomodation.AccomodationRule;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "ACCOMODATION_ACC_RULE")
public class AccomodationAccRuleModel implements Serializable{

	private static final long serialVersionUID = 5337137655793539204L;
	
	@EmbeddedId
	private AccomodationAccRuleId accomodationAccRuleId;
}
