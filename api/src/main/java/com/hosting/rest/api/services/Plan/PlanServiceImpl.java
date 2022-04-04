package com.hosting.rest.api.services.Plan;

import static com.hosting.rest.api.Utils.AppUtils.isBigDecimalValid;
import static com.hosting.rest.api.Utils.AppUtils.isIntegerValidAndPositive;
import static com.hosting.rest.api.Utils.AppUtils.isNotNull;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hosting.rest.api.exceptions.IllegalArguments.IllegalArgumentsCustomException;
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
	public PlanModel addNewPlan(final PlanModel planToAdd) {
		if (!isNotNull(planToAdd)) {
			throw new IllegalArgumentsCustomException("Los datos del plan a añadir no existen o están incompletos.");
		}
		
		System.out.println(planToAdd);

		boolean existsPlan = planRepo.existsById(planToAdd.getIdPlan());

		if (existsPlan) {
			throw new IllegalArgumentsCustomException("El plan a añadir ya existe.");
		}

		return planRepo.save(planToAdd);
	}

	@Transactional
	@Override
	public void udpatePlan(final Integer planId, final PlanModel planToUpdate) throws NumberFormatException {

		if (!isIntegerValidAndPositive(planId)) {
			throw new IllegalArgumentsCustomException("El id del plan " + planId + " no es válido.");
		}

		if (!isNotNull(planToUpdate)) {
			throw new IllegalArgumentsCustomException("El contenido del plan a actualizar no es válido.");
		}

		if (!isBigDecimalValid(planToUpdate.getPrice())) {
			throw new IllegalArgumentsCustomException("El precio del plan no es válido.");
		}

		boolean existsPlanToUpdate = planRepo.existsById(planId);

		if (!existsPlanToUpdate) {
			throw new NotFoundCustomException("No existe un plan con id [ " + planId + " ].");
		}

//		PlanModel originalPlan = getPlanById(planId);

		String updatePlanModelQuery = "UPDATE PlanModel pm SET pm.price = :planPrice WHERE pm.idPlan = :planId";

		Query updatedPlan = em.createQuery(updatePlanModelQuery);

		updatedPlan.setParameter("planPrice", planToUpdate.getPrice());
		updatedPlan.setParameter("planId", planId);

		updatedPlan.executeUpdate();
		// Nuevo precio del plan
//		originalPlan.setPrice(planToUpdate.getPrice());
//
//		return planRepo.save(originalPlan);
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
