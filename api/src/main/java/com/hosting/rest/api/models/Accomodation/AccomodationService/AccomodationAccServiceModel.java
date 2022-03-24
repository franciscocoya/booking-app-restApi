package com.hosting.rest.api.models.Accomodation.AccomodationService;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "ACCOMODATION_ACC_SERVICE")
public class AccomodationAccServiceModel {

	@EmbeddedId
	private AccomodationAccServiceId accomodationAccServiceId;
}
