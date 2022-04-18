package com.hosting.rest.api.services.Plan;

import java.math.BigDecimal;
import java.util.List;

import com.hosting.rest.api.models.Plan.PlanModel;

public interface IPlanService {

	public PlanModel addNewPlan(final PlanModel planToAdd);

	public void udpatePlan(final Integer planId, final BigDecimal newPrice);

	public PlanModel getPlanById(final Integer planId);

	public void deletePlanById(final Integer planId);

	public List<PlanModel> findAllPlans();

	public PlanModel findByUserId(final Integer userId);
}
