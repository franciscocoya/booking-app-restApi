package com.hosting.rest.api.models.Accomodation;


import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "ACCOMODATION_SERVICE_EXTRA")
@Data
public class AccomodationServiceExtraModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "PRICE")
    private BigDecimal price;
}
