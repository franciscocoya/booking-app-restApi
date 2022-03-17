package com.hosting.rest.api.models.Accomodation;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table( name = "ACCOMODATION_CATEGORY" )
@Data
public class AccomodationCategoryModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name = "ACC_CAT")
    private int accomodationCategory;
}
