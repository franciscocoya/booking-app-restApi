package com.hosting.rest.api.models.Accomodation;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "ACCOMODATION_RULE")
@Data
public class AccomodationRuleModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "RULE")
    private String rule;
}
