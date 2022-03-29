package com.hosting.rest.api.models.Accomodation.AccomodationService;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "ACCOMODATION_SERVICE_EXTRA")
@PrimaryKeyJoinColumn(name = "ID")
public class AccomodationServiceExtraModel extends AccomodationServiceModel {

	@Column(name = "PRICE")
	private BigDecimal price;
}
