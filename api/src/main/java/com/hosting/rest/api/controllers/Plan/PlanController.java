package com.hosting.rest.api.controllers.Plan;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hosting.rest.api.models.Plan.PlanModel;
import com.hosting.rest.api.services.Plan.PlanServiceImpl;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/plans")
public class PlanController {

	@Autowired
	private PlanServiceImpl planService;

	@PostMapping("new")
	public PlanModel addNewPlan(@RequestBody PlanModel planToAdd) {
		return planService.addNewPlan(planToAdd);
	}
	
	@PutMapping("{planId}")
	public PlanModel udpatePlan(@PathVariable(name = "planId") Integer planId, @RequestBody PlanModel planToUpdate) {
		return planService.udpatePlan(planId, planToUpdate);
	}
	
	@GetMapping("{planId}")
	public PlanModel getPlanById(@PathVariable Integer planId) {
		return planService.getPlanById(planId);
	}
	
	@DeleteMapping("{planId}")
	public void deletePlanById(@PathVariable Integer planId) {
		planService.deletePlanById(planId);
	}
	
	@GetMapping("all")
	public List<PlanModel> listAllPlans() {
		return planService.listAllPlans();
	}
	
}
