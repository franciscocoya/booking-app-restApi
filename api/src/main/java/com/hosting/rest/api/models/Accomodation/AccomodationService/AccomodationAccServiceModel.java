package com.hosting.rest.api.models.Accomodation.AccomodationService;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@AllArgsConstructor
@RequiredArgsConstructor
@Data
@Table(name = "ACCOMODATION_ACC_SERVICE")
public class AccomodationAccServiceModel implements Serializable{

	private static final long serialVersionUID = 8776073192577384352L;
	
	@EmbeddedId
	private AccomodationAccServiceId accomodationAccServiceId;
}
