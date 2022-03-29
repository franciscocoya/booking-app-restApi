package com.hosting.rest.api.services.Plan;

import java.util.List;

import com.hosting.rest.api.models.Plan.PlanModel;

public interface IPlanService {
	
	public PlanModel addNewPlan(final PlanModel planToAdd);
	
	public PlanModel udpatePlan(final Integer planId, final PlanModel planToUpdate);
	
	public PlanModel getPlanById(final Integer planId);
	
	public void deletePlanById(final Integer planId);
	
	public List<PlanModel> findAllPlans();
	
	public PlanModel findByUserId(final Integer userId);
}
