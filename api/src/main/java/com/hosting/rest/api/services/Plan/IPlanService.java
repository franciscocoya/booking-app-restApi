package com.hosting.rest.api.services.Plan;

import java.util.List;

import com.hosting.rest.api.models.Plan.PlanModel;

public interface IPlanService {
	
	PlanModel addNewPlan(PlanModel planToAdd);
	
	PlanModel udpatePlan(Integer planId, PlanModel planToUpdate);
	
	PlanModel getPlanById(Integer planId);
	
	void deletePlanById(Integer planId);
	
	List<PlanModel> listAllPlans();
}
