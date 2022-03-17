package com.hosting.rest.api.models.Accomodation;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table( name = "ACCOMODATION_ACC_SERVICE" )
@Data
public class AccomodationAccServiceModel {

    @Id
    @Column(name = "ID_ACC")
    private String idAccomodation;

    @Id
    @Column(name = "ID_ACC_SERVICE")
    private int idAccomodationService;
}
