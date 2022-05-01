package com.hosting.rest.api.models.Accomodation;


import lombok.Data;

import javax.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "ACCOMODATION_LOCATION")
@Data
public class AccomodationLocationModel implements Serializable{
	
	private static final long serialVersionUID = -817371871874015346L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name = "LAT")
    private BigDecimal latitude;

    @Column(name = "LNG")
    private BigDecimal longitude;

    @Column(name = "DIRECTION")
    private String direction;

    @Column(name = "CITY")
    private String city;

    @Column(name = "ZIP")
    private String zip;
}
