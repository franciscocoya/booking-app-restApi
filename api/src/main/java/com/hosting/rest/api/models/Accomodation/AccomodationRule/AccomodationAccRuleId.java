package com.hosting.rest.api.models.Accomodation.AccomodationRule;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class AccomodationAccRuleId implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String idAccomodation;
    private int idAccomodationRule;
}
