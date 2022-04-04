package com.hosting.rest.api.services.Plan.PlanSubscription;

import java.util.List;

import com.hosting.rest.api.models.Plan.PlanSubscriptionModel;

public interface IPlanSubscriptionService {

	public PlanSubscriptionModel addNewPlanSubscription(final PlanSubscriptionModel planSubscriptionToAdd);

	public void deletePlanSubscriptionByUserHostId(final Integer userHostId);

	public PlanSubscriptionModel getPlanSubscriptionByUserHostId(final Integer userHostId);

	public List<PlanSubscriptionModel> findAllByPlanId(final Integer planId);
}
