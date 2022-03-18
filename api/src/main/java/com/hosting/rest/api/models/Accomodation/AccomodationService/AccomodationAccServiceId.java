package com.hosting.rest.api.models.Accomodation.AccomodationService;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 
 * @author Francisco Coya
 *
 */
@AllArgsConstructor
@Data
public class AccomodationAccServiceId implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String idAccomodation;
	private int idAccomodationService;
}
