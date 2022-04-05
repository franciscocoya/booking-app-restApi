package com.hosting.rest.api.controllers.Plan;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hosting.rest.api.exceptions.IllegalArguments.IllegalArgumentsCustomException;
import com.hosting.rest.api.exceptions.NotFound.NotFoundCustomException;
import com.hosting.rest.api.models.Plan.PlanModel;
import com.hosting.rest.api.services.Plan.PlanServiceImpl;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/plans")
public class PlanController {

	@Autowired
	private PlanServiceImpl planService;

	@PostMapping("new")
	public PlanModel addNewPlan(@RequestBody final PlanModel planToAdd) {
		return planService.addNewPlan(planToAdd);
	}

	@PutMapping("{planId}")
	public void udpatePlan(@PathVariable(name = "planId") final String planId,
			@Valid @RequestParam(name = "price") final BigDecimal newPrice) {

		try {
			planService.udpatePlan(Integer.parseInt(planId), newPrice);

		} catch (NumberFormatException nfe) {
			throw new IllegalArgumentsCustomException("El id del plan [ " + planId + " ] no es válido.");
		}
	}

	@GetMapping("{planId}")
	public PlanModel getPlanById(@PathVariable final String planId) {

		PlanModel planToReturn = null;

		try {
			planToReturn = planService.getPlanById(Integer.parseInt(planId));

		} catch (NumberFormatException nfe) {
			throw new IllegalArgumentsCustomException("El id del plan [" + planId + " no es válido.");

		} catch (NoSuchElementException nsee) {
			throw new NotFoundCustomException("No existe un plan con id [ " + planId + " ].");
		}

		return planToReturn;
	}

	@DeleteMapping("{planId}")
	public void deletePlanById(@PathVariable final String planId) {

		try {
			planService.deletePlanById(Integer.parseInt(planId));

		} catch (NumberFormatException nfe) {
			throw new IllegalArgumentsCustomException("El id del plan [ " + planId + " ] no es válido.");
		}
	}

	@GetMapping("all")
	public List<PlanModel> findAllPlans() {
		return planService.findAllPlans();
	}

	@GetMapping("{userId}/activePlan")
	public PlanModel findUserPlan(@PathVariable(value = "userId") final String userId) {
		PlanModel userToReturn = null;

		try {
			userToReturn = planService.findByUserId(Integer.parseInt(userId));

		} catch (NumberFormatException e) {
			throw new IllegalArgumentsCustomException("El id de usuario [ " + userId + " ] no es válido.");
		}

		if (userToReturn == null) {
			throw new NotFoundCustomException("El usuario con id [ " + userId
					+ " ] no tiene ningún plan activo. Para crear un plan tiene que ser HOST.");
		}

		return userToReturn;
	}

}
