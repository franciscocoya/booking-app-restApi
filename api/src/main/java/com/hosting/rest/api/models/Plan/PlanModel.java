package com.hosting.rest.api.models.Plan;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
@AllArgsConstructor
@Table(name = "APP_PLAN")
public class PlanModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer idPlan;

    @Column(name = "PLAN_TYPE")
    private PlanType planType;

    @Column(name = "PRICE")
    private BigDecimal price;
}
