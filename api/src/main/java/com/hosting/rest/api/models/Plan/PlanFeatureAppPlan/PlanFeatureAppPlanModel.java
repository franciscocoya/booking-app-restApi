package com.hosting.rest.api.models.Plan.PlanFeatureAppPlan;

import com.hosting.rest.api.models.Plan.PlanModel;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@Table(name = "PLAN_FEATURE_APP_PLAN")
public class PlanFeatureAppPlanModel {

    @EmbeddedId
    private PlanFeatureAppPlanId planFeatureAppPlanId;

}
