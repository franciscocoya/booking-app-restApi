package com.hosting.rest.api.services.Plan;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.hosting.rest.api.Utils.AppUtils.isIntegerValid;
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
	public List<PlanModel> findAllPlans() {
		return planRepo.findAll();
	}

	@Override
	public PlanModel getPlanById(final Integer planId) throws IllegalArgumentException{
		
		return planRepo.findById(planId).get();
	}

	@Override
	public PlanModel findByUserId(final Integer userId) throws IllegalArgumentException, NoResultException {

		if (!isIntegerValid(userId)) {
			return null;
		}

		/**
		 * Plan de subscripción del usuario host.
		 */
		String findCurrentUserPlanQuery = "select pm"
				+ " from PlanSubscriptionModel psm inner join psm.planSubscriptionUserHostId s, PlanModel pm"
				+ " where s.idUserHost = :userId and pm.idPlan = s.idPlan";

		TypedQuery<PlanModel> currentPlan = em.createQuery(findCurrentUserPlanQuery, PlanModel.class);

		currentPlan.setParameter("userId", userId);

		return currentPlan.getSingleResult();
	}

}
