package com.hosting.rest.api.models.Accomodation;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "ACCOMODATION")
@Data
public class AccomodationModel {

    @Id
    @Column(name = "REG_NUM")
    private String registerNumber;

    @Column(name = "BEDS")
    private Integer numOfBeds;

    @Column(name = "NUM_BATHROOMS")
    private Integer numOfBathRooms;

    @Column(name = "NUM_BEDROOMS")
    private Integer numOfBedRooms;

    @Column(name = "PRICE_PER_NIGHT")
    private BigDecimal pricePerNight;

    @Column(name = "GUEST")
    private Integer numOfGuests;

    @Column(name = "AREA")
    private BigDecimal area;

    @Column(name = "ID_ACC_CATEGORY")
    private Integer idAccomodationCategory;

    @Column(name = "ID_ACC_LOCATION")
    private Integer idAccomodationLocation;

}
