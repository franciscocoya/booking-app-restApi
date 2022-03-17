package com.hosting.rest.api.models.Accomodation;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "ACCOMODATION_IMAGE")
@Data
public class AccomodationImageModel {
    @Column(name = "ID")
    private int id;

    @Column(name = "IMG_URL")
    private String imageUrl;

    @Column(name = "ID_ACCOMODATION")
    private String idAccomodation;
}
