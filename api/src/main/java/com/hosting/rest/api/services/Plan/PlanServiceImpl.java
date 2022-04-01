package com.hosting.rest.api.services.Plan;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import com.hosting.rest.api.exceptions.NotFound.NotFoundCustomException;
import com.hosting.rest.api.models.Plan.PlanModel;
import com.hosting.rest.api.repositories.Plan.IPlanRepository;

@Service
public class PlanServiceImpl implements IPlanService {

	@PersistenceContext
	private EntityManager em;

	@Autowired
	private IPlanRepository planRepo;

	@Override
	public PlanModel addNewPlan(final PlanModel planToAdd) throws NullPointerException {
		return planRepo.save(planToAdd);
	}

	@Modifying
	@Override
	public PlanModel udpatePlan(final Integer planId, final PlanModel planToUpdate)
			throws NumberFormatException, NullPointerException {
		
		boolean existsPlanToUpdate = planRepo.existsById(planId);

		if (!existsPlanToUpdate) {
			throw new NotFoundCustomException("No existe un plan con id [ " + planId + " ].");
		}

		PlanModel originalPlan = getPlanById(planId);
		// Nuevo precio del plan
		originalPlan.setPrice(planToUpdate.getPrice());

		return planRepo.save(originalPlan);
	}

	@Override
	public void deletePlanById(final Integer planId) throws IllegalArgumentException, NumberFormatException {

		boolean existsPlanToRemove = planRepo.existsById(planId);

		if (existsPlanToRemove) {
			planRepo.deleteById(planId);
		}
	}

	@Override
	public List<PlanModel> findAllPlans() {
		return planRepo.findAll();
	}

	@Override
	public PlanModel getPlanById(final Integer planId) throws NumberFormatException, IllegalArgumentException {
		return planRepo.findById(planId).get();
	}

	@Override
	public PlanModel findByUserId(final Integer userId) throws IllegalArgumentException, NumberFormatException {
		/**
		 * Plan de subscripción del usuario host.
		 */
		String findCurrentUserPlanQuery = "SELECT pm "
				+ "FROM PlanSubscriptionModel psm INNER JOIN psm.planSubscriptionUserHostId s, PlanModel pm "
				+ "WHERE s.idUserHost = :userId AND pm.idPlan = s.idPlan";

		TypedQuery<PlanModel> currentPlan = em.createQuery(findCurrentUserPlanQuery, PlanModel.class);

		currentPlan.setParameter("userId", userId);

		return currentPlan.getSingleResult();
	}

}
