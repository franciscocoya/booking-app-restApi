package com.hosting.rest.api.models.Accomodation.AccomodationRule;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "ACCOMODATION_ACC_RULE")
@Data
public class AccomodationAccRuleModel implements Serializable{

	private static final long serialVersionUID = 5337137655793539204L;
	
	@EmbeddedId
	private AccomodationAccRuleId accomodationAccRuleId;
}
