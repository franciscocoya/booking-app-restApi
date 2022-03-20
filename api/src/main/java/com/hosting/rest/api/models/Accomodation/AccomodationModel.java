package com.hosting.rest.api.models.Accomodation;


import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

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

    @OneToOne
    @JoinColumn(name = "ID_ACC_CATEGORY")
    private AccomodationCategoryModel idAccomodationCategory;

    @OneToOne
    @JoinColumn(name = "ID_ACC_LOCATION")
    private AccomodationLocationModel idAccomodationLocation;

}
