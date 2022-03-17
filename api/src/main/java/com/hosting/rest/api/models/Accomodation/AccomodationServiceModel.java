package com.hosting.rest.api.models.Accomodation;


import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "ACCOMODATION_SERVICE")
@Data
public class AccomodationServiceModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name = "DENOMINATION")
    private String denomination;
}
