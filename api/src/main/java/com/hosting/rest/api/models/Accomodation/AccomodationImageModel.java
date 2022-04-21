package com.hosting.rest.api.models.Accomodation;


import java.io.Serializable;

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
@Data
@Table(name = "ACCOMODATION_IMAGE")
public class AccomodationImageModel implements Serializable{
	
	private static final long serialVersionUID = -1011564744279482111L;

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
