package com.hosting.rest.api.controllers.Plan.PlanSubscription;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hosting.rest.api.exceptions.IllegalArguments.IllegalArgumentsCustomException;
import com.hosting.rest.api.models.Plan.PlanSubscriptionModel;
import com.hosting.rest.api.services.Plan.PlanSubscription.PlanSubscriptionServiceImpl;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author Francisco Coya
 * @version v1.0.3
 * @apiNote Controlador de la subscripción de usuarios host a planes.
 *
 */
@RestController
@RequestMapping("/plans/subscriptions")
@Slf4j
public class PlanSubscriptionController {

	@Autowired
	private PlanSubscriptionServiceImpl planSubscriptionService;

	@PreAuthorize("hasRole('ROLE_HOST_USER') or hasRole('ROLE_ADMIN_USER')")
	@PostMapping("new")
	public PlanSubscriptionModel addNewPlanSubscription(
			@RequestBody final PlanSubscriptionModel planSubscriptionToAdd) {
		return planSubscriptionService.addNewPlanSubscription(planSubscriptionToAdd);
	}

	@PreAuthorize("hasRole('ROLE_HOST_USER') or hasRole('ROLE_ADMIN_USER')")
	@GetMapping("{userHostId}")
	public PlanSubscriptionModel getPlanSubscriptionByUserHostId(
			@PathVariable(value = "userHostId") final String userHostId) {
		PlanSubscriptionModel planSubscriptionToReturn = null;

		try {
			planSubscriptionToReturn = planSubscriptionService
					.getPlanSubscriptionByUserHostId(Integer.parseInt(userHostId));

		} catch (NumberFormatException nfe) {
			log.error("El id [ " + userHostId + " ] de usuario no es un número.");
			throw new IllegalArgumentsCustomException("El id [ " + userHostId + " ] de usuario no es un número.");
		}

		return planSubscriptionToReturn;
	}

	@PreAuthorize("hasRole('ROLE_HOST_USER') or hasRole('ROLE_ADMIN_USER')")
	@DeleteMapping("{userHostId}")
	public void deletePlanSubscriptionByUserHostId(@PathVariable(value = "userHostId") final String userHostId) {
		try {
			planSubscriptionService.deletePlanSubscriptionByUserHostId(Integer.parseInt(userHostId));

		} catch (NumberFormatException nfe) {
			log.error("El id [ " + userHostId + " ] de usuario no es un número.");
			throw new IllegalArgumentsCustomException("El id [ " + userHostId + " ] de usuario no es un número.");
		}
	}

	@PreAuthorize("hasRole('ROLE_ADMIN_USER')")
	@GetMapping("{planId}/users")
	public List<PlanSubscriptionModel> getPlanSubscriptionUsersByPlanId(
			@PathVariable(value = "planId") final String planId) {
		List<PlanSubscriptionModel> planSubscriptionUsers = null;

		try {
			planSubscriptionUsers = planSubscriptionService.findAllByPlanId(Integer.parseInt(planId));

		} catch (NumberFormatException nfe) {
			log.error("El id [ " + planId + " ] del plan de subscripción no es un número.");
			throw new IllegalArgumentsCustomException(
					"El id [ " + planId + " ] del plan de subscripción no es un número.");
		}

		return planSubscriptionUsers;
	}
}
