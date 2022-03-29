package com.hosting.rest.api.controllers.Plan;

import java.util.List;
import java.util.NoSuchElementException;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.server.ResponseStatusException;

import com.hosting.rest.api.exceptions.Plan.IllegalArgument.IllegalPlanArgumentsException;
import com.hosting.rest.api.exceptions.Plan.NotFound.PlanNotFoundException;
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
		PlanModel planToReturn = null;
		try {
			planToReturn = planService.addNewPlan(planToAdd);

		} catch (IllegalArgumentException iae) {
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "");
		}

		return planToReturn;
	}

	@PutMapping("{planId}")
	public PlanModel udpatePlan(@PathVariable(name = "planId") final Integer planId,
			@RequestBody final PlanModel planToUpdate) {

		PlanModel udpatedPlan = null;

		try {
			udpatedPlan = planService.udpatePlan(planId, planToUpdate);

		} catch (IllegalArgumentException iae) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No fue posible actualizar los datos del plan.");
		}

		return udpatedPlan;
	}

	@GetMapping("{planId}")
	public PlanModel getPlanById(@PathVariable final Integer planId) {

		PlanModel planToReturn = null;

		try {
			planToReturn = planService.getPlanById(planId);

		} catch (NumberFormatException | MethodArgumentTypeMismatchException nfe) {
			throw new IllegalPlanArgumentsException(planId);

		} catch (NoSuchElementException nsee) {
			throw new PlanNotFoundException(planId);
		}

		return planToReturn;
	}

	@DeleteMapping("{planId}")
	public void deletePlanById(@PathVariable final Integer planId) {
		planService.deletePlanById(planId);
	}

	@GetMapping("all")
	public List<PlanModel> findAllPlans() {
		return planService.findAllPlans();
	}

	@GetMapping("{userId}/activePlan")
	public PlanModel findUserPlan(@PathVariable(value = "userId") final Integer userId) {
		PlanModel userToReturn = null;

		try {
			userToReturn = planService.findByUserId(userId);

		} catch (IllegalArgumentException | NoResultException iae) {
			throw new PlanNotFoundException(userId);
			
		}

		return userToReturn;
	}

}
