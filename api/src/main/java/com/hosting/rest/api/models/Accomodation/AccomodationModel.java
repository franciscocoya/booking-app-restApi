package com.hosting.rest.api.models.Accomodation;


import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;

import com.hosting.rest.api.models.User.UserHostModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ACCOMODATION")
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
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_USER_OWNER")
    private UserHostModel idUserHost;

    @Column(name = "CREATED_AT")
    @CreatedDate
    private LocalDateTime createdAt;

}
