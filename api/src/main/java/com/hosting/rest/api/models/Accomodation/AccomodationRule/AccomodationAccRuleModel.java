package com.hosting.rest.api.models.Accomodation.AccomodationRule;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.IdClass;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table( name = "ACCOMODATION_ACC_RULE" )
@Data
public class AccomodationAccRuleModel {

	@EmbeddedId
   private AccomodationAccRuleId accomodationAccRuleId;
}	
