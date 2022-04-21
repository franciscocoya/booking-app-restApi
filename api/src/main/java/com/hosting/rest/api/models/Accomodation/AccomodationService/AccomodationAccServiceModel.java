package com.hosting.rest.api.models.Accomodation.AccomodationService;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "ACCOMODATION_ACC_SERVICE")
public class AccomodationAccServiceModel implements Serializable{

	private static final long serialVersionUID = 8776073192577384352L;
	
	@EmbeddedId
	private AccomodationAccServiceId accomodationAccServiceId;
}
