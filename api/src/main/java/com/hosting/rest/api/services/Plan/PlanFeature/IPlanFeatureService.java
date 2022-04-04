package com.hosting.rest.api.services.Plan.PlanFeature;

import java.util.List;

import com.hosting.rest.api.models.Plan.PlanFeatureAppPlan.PlanFeatureModel;

public interface IPlanFeatureService {

	public PlanFeatureModel addNewPlanFeature(final PlanFeatureModel planFeatureToAdd);

	public void deleteById(final Integer planFeatureId);

	public void updatePlanFeature(final Integer planFeatureId, final PlanFeatureModel planFeatureToUpdate);

	public List<PlanFeatureModel> findAllByPlanId(final Integer planId);
}
