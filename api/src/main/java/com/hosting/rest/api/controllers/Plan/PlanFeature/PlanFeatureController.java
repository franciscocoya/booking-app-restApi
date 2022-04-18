package com.hosting.rest.api.controllers.Plan.PlanFeature;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hosting.rest.api.exceptions.IllegalArguments.IllegalArgumentsCustomException;
import com.hosting.rest.api.models.Plan.PlanFeatureAppPlan.PlanFeatureModel;
import com.hosting.rest.api.services.Plan.PlanFeature.PlanFeatureServiceImpl;

@RestController
@RequestMapping("/plans/features")
public class PlanFeatureController {

	@Autowired
	private PlanFeatureServiceImpl planFeatureService;

	@PostMapping("new")
	public PlanFeatureModel addNewPlanFeature(@RequestBody PlanFeatureModel planFeatureToAdd) {
		return planFeatureService.addNewPlanFeature(planFeatureToAdd);
	}

	@PutMapping("{planFeatureId}")
	public void updatePlanFeatureById(@PathVariable(value = "planFeatureId") final String planFeatureId,
			@RequestBody PlanFeatureModel planFeatureToUpdate) {

		try {
			planFeatureService.updatePlanFeature(Integer.parseInt(planFeatureId), planFeatureToUpdate);
		} catch (NumberFormatException nfe) {
			throw new IllegalArgumentsCustomException("El id de la característica del plan no es un número.");
		}
	}

	@DeleteMapping("{planFeatureId}")
	public void deletePlanFeatureById(@PathVariable(value = "planFeatureId") final String planFeatureId) {

		try {
			planFeatureService.deleteById(Integer.parseInt(planFeatureId));

		} catch (NumberFormatException nfe) {
			throw new IllegalArgumentsCustomException("El id de la característica a eliminar no es un número.");
		}
	}

	@GetMapping("{planId}/all")
	public List<PlanFeatureModel> findAllByPlanId(@PathVariable(value = "planId") final String planId) {
		List<PlanFeatureModel> planFeaturesListToReturn = null;

		try {
			planFeaturesListToReturn = planFeatureService.findAllByPlanId(Integer.parseInt(planId));

		} catch (NumberFormatException nfe) {
			throw new IllegalArgumentsCustomException("El id del plan introducido no es un número.");
		}

		return planFeaturesListToReturn;
	}
}
