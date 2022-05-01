package com.hosting.rest.api.models.Plan.PlanFeatureAppPlan;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "PLAN_FEATURE_APP_PLAN")
public class PlanFeatureAppPlanModel implements Serializable{

    private static final long serialVersionUID = -7237864221975856964L;
    
	@EmbeddedId
    private PlanFeatureAppPlanId planFeatureAppPlanId;
}
