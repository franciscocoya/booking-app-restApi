package com.hosting.rest.api.models.Accomodation;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "ACCOMODATION_IMAGE")
@Data
public class AccomodationImageModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name = "IMG_URL")
    private String imageUrl;

    @ManyToOne
    @JoinColumn(name = "REG_NUM")
    private AccomodationModel accomodation;
    
}
