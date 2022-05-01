package com.hosting.rest.api.models.Plan.PlanFeatureAppPlan;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "PLAN_FEATURE")
public class PlanFeatureModel implements Serializable {

    private static final long serialVersionUID = -5510683410703805897L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer idPlanFeature;

    @Column(name = "DETAIL")
    private String planFeatureDetail;
}
