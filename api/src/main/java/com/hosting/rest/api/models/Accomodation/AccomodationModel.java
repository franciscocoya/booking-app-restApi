package com.hosting.rest.api.models.Accomodation;


import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.*;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

@Entity
@Table(name = "ACCOMODATION")
@Data
public class AccomodationModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    @Column(name = "GUESTS")
    private Integer numOfGuests;

    @Column(name = "AREA")
    private BigDecimal area;

    @OneToOne
    @JoinColumn(name = "ID_ACC_CATEGORY")
    private AccomodationCategoryModel idAccomodationCategory;

    @OneToOne
    @JoinColumn(name = "ID_ACC_LOCATION")
    private AccomodationLocationModel idAccomodationLocation;

    @Column(name = "CREATED_AT")
    @CreatedDate
    private LocalDateTime createdAt;

}
