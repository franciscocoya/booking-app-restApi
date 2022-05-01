package com.hosting.rest.api.models.Accomodation.AccomodationImage;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Embeddable
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class AccomodationAccImageId implements Serializable {

	private static final long serialVersionUID = 4776860958061126067L;
	
	@Column(name = "ID_ACC")
	private String idAccomodation;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_ACC_IMAGE")
	private AccomodationImageModel idAccomodationImage;

}
