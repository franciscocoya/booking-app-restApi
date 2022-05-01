package com.hosting.rest.api.models.Accomodation.AccomodationImage;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;


@Entity
@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Table(name = "ACCOMODATION_ACC_IMAGE")
public class AccomodationAccImageModel implements Serializable {
	
	private static final long serialVersionUID = 7795405118650294443L;
	
	@EmbeddedId
	private AccomodationAccImageId accomodationAccImageId;

}
