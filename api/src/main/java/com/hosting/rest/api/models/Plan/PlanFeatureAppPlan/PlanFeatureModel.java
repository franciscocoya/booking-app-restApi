package com.hosting.rest.api.models.Plan.PlanFeatureAppPlan;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "PLAN_FEATURE")
public class PlanFeatureModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer idPlanFeature;

    @Column(name = "DETAIL")
    private String planFeatureDetail;
}
