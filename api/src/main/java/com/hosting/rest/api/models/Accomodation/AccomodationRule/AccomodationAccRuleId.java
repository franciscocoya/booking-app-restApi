package com.hosting.rest.api.models.Accomodation.AccomodationRule;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Em
@Data
public class AccomodationAccRuleId implements Serializable{
	
	private String idAccomodation;
    private int idAccomodationRule;
}
