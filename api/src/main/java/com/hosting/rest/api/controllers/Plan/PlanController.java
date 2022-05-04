package com.hosting.rest.api.controllers.Plan;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author Francisco Coya
 * @version v1.0.3
 * @apiNote Controlador de los planes de subscripción de un usuario host.
 *
 */
@RestController
@CrossOrigin(origins = {"*"})
@RequestMapping("/plans")
@Slf4j
public class PlanController {

	@Autowired
	private PlanServiceImpl planService;

	@PreAuthorize("hasRole('ROLE_HOST_USER') or hasRole('ROLE_ADMIN_USER')")
	@PostMapping("new")
	public PlanModel addNewPlan(@Valid @RequestBody final PlanModel planToAdd) {
		return planService.addNewPlan(planToAdd);
	}

	@PreAuthorize("hasRole('ROLE_HOST_USER') or hasRole('ROLE_ADMIN_USER')")
	@PutMapping("{planId}")
	public void udpatePlan(@PathVariable(name = "planId") final String planId,
			@RequestParam(name = "price") final BigDecimal newPrice) {

		try {
			planService.udpatePlan(Integer.parseInt(planId), newPrice);

		} catch (NumberFormatException nfe) {
			log.error("El id del plan [ " + planId + " ] no es válido.");
			throw new IllegalArgumentsCustomException("El id del plan [ " + planId + " ] no es válido.");
		}
	}

	@PreAuthorize("hasRole('ROLE_BASE_USER') or hasRole('ROLE_HOST_USER') or hasRole('ROLE_ADMIN_USER')")
	@GetMapping("{planId}")
	public PlanModel getPlanById(@PathVariable final String planId) {

		PlanModel planToReturn = null;

		try {
			planToReturn = planService.getPlanById(Integer.parseInt(planId));

		} catch (NumberFormatException nfe) {
			log.error("El id del plan [" + planId + " no es válido.");
			throw new IllegalArgumentsCustomException("El id del plan [" + planId + " no es válido.");

		} catch (NoSuchElementException nsee) {
			log.error("No existe un plan con id [ " + planId + " ].");
			throw new NotFoundCustomException("No existe un plan con id [ " + planId + " ].");
		}

		return planToReturn;
	}

	@PreAuthorize("hasRole('ROLE_HOST_USER') or hasRole('ROLE_ADMIN_USER')")
	@DeleteMapping("{planId}")
	public void deletePlanById(@PathVariable final String planId) {

		try {
			planService.deletePlanById(Integer.parseInt(planId));

		} catch (NumberFormatException nfe) {
			log.error("El id del plan [ " + planId + " ] no es válido.");
			throw new IllegalArgumentsCustomException("El id del plan [ " + planId + " ] no es válido.");
		}
	}

	@PreAuthorize("hasRole('ROLE_BASE_USER') or hasRole('ROLE_HOST_USER') or hasRole('ROLE_ADMIN_USER')")
	@GetMapping("all")
	public List<PlanModel> findAllPlans() {
		return planService.findAllPlans();
	}

	@PreAuthorize("hasRole('ROLE_HOST_USER') or hasRole('ROLE_ADMIN_USER')")
	@GetMapping("{userId}/activePlan")
	public PlanModel findUserPlan(@PathVariable(value = "userId") final String userId) {
		PlanModel userToReturn = null;

		try {
			userToReturn = planService.findByUserId(Integer.parseInt(userId));

		} catch (NumberFormatException e) {
			log.error("El id de usuario [ " + userId + " ] no es válido.");
			throw new IllegalArgumentsCustomException("El id de usuario [ " + userId + " ] no es válido.");
		}

		return userToReturn;
	}
}
