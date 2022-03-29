package com.hosting.rest.api.services.Plan;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hosting.rest.api.models.Plan.PlanModel;
import com.hosting.rest.api.repositories.Plan.IPlanRepository;

@Service
public class PlanServiceImpl implements IPlanService {

	@Autowired
	private IPlanRepository planRepo;

	@Override
	public PlanModel addNewPlan(final PlanModel planToAdd) {
		return planRepo.save(planToAdd);
	}

	@Override
	public PlanModel udpatePlan(final Integer planId, final PlanModel planToUpdate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deletePlanById(final Integer planId) {
		planRepo.deleteById(planId);
	}

	@Override
	public List<PlanModel> listAllPlans() {
		return planRepo.findAll();
	}

	@Override
	public PlanModel getPlanById(final Integer planId) {
		return planRepo.findById(planId).get();
	}

}