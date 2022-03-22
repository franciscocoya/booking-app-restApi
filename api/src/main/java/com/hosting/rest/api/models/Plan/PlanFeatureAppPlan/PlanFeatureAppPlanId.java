package com.hosting.rest.api.models.Plan.PlanFeatureAppPlan;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * @author Francisco Coya · https://github.com/FranciscoCoya
 * @version v1.0.0
 * @description
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class PlanFeatureAppPlanId implements Serializable {

    @Column(name = "ID_PLAN")
    private Integer idPlan;

    @Column(name = "ID_PLAN_FEATURE")
    private Integer idPlanFeature;
}
