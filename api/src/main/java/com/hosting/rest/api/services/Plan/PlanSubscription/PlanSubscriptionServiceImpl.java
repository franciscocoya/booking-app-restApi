package com.hosting.rest.api.services.Plan.PlanSubscription;

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
import com.hosting.rest.api.models.Plan.PlanSubscriptionModel;
import com.hosting.rest.api.repositories.Plan.PlanSubscription.IPlanSubscriptionRepository;

@Service
public class PlanSubscriptionServiceImpl implements IPlanSubscriptionService {

	@PersistenceContext
	private EntityManager em;

	@Autowired
	private IPlanSubscriptionRepository planSubscriptionRepo;

	@Override
	public PlanSubscriptionModel addNewPlanSubscription(final PlanSubscriptionModel planSubscriptionToAdd) {
		if (!isNotNull(planSubscriptionToAdd)) {
			throw new IllegalArgumentsCustomException(
					"Alguno de los valores de la subscripción del plan no es válido.");
		}

		Integer planSubscriptionUserHostId = planSubscriptionToAdd.getPlanSubscriptionUserHostId().getIdUserHost();

		// Sólo los usuarios host están suscritos a un plan
		if (!isUserHost(planSubscriptionUserHostId)) {
			throw new IllegalArgumentsCustomException("El usuario con id [ " + planSubscriptionUserHostId
					+ " ] no es host. Sólamente los usuarios host pueden estar suscritos a un plan");
		}

		// Comprobar que no existe el plan de subscriptción a añadir. Para ello,
		// un usuario no puede tener asociado más de un plan.
		if (existsPlanSubscriptionByUserHostId(planSubscriptionUserHostId)) {
			throw new IllegalArgumentsCustomException(
					"El usuario [ " + planSubscriptionUserHostId + " ] ya tiene asociado un plan de subscripción.");
		}

		return planSubscriptionRepo.save(planSubscriptionToAdd);
	}

	/**
	 * Comprueba que un usuario con id <code>userHostId</code> es un usuario host.
	 * 
	 * @param userHostId
	 * @return
	 */
	private boolean isUserHost(final Integer userHostId) {
		String isUserHostQuery = "SELECT COUNT(uhm) > 0 FROM UserHostModel uhm WHERE uhm.id = :userHostId";

		TypedQuery<Boolean> isUserHostResult = em.createQuery(isUserHostQuery, Boolean.class);

		isUserHostResult.setParameter("userHostId", userHostId);

		return isUserHostResult.getSingleResult();
	}

	/**
	 * Comprueba si un usuario host tiene asociada ya una subscripción. Devuelve
	 * true en caso verdadero y false en otro caso.
	 * 
	 * @param userHostId
	 * @return
	 */
	private boolean existsPlanSubscriptionByUserHostId(final Integer userHostId) {
		String existsPlanSubscriptionByUserHostIdQuery = "SELECT COUNT(psm) > 0 " + "FROM PlanSubscriptionModel psm "
				+ "WHERE psm.planSubscriptionUserHostId.idUserHost = :userHostId";

		TypedQuery<Boolean> existsSubscription = em.createQuery(existsPlanSubscriptionByUserHostIdQuery, Boolean.class);

		existsSubscription.setParameter("userHostId", userHostId);

		return existsSubscription.getSingleResult();
	}
	

	@Transactional
	@Override
	public void deletePlanSubscriptionByUserHostId(final Integer userHostId) {
		if (!isIntegerValidAndPositive(userHostId)) {
			throw new IllegalArgumentsCustomException("El id del usuario host no es válido.");
		}

		boolean existsPlanSubscription = getPlanSubscriptionByUserHostId(userHostId) != null;

		if (!existsPlanSubscription) {
			throw new IllegalArgumentsCustomException("La subscripción al plan ya existe.");
		}

		String deletePlanSubscriptionByUserHostIdQuery = "DELETE FROM PlanSubscriptionModel psm "
				+ "WHERE psm.planSubscriptionUserHostId.idUserHost = :userHostId";

		Query deletedPlanSubscription = em.createQuery(deletePlanSubscriptionByUserHostIdQuery);

		deletedPlanSubscription.setParameter("userHostId", userHostId);

		deletedPlanSubscription.executeUpdate();
	}

	@Override
	public PlanSubscriptionModel getPlanSubscriptionByUserHostId(final Integer userHostId) {
		if (!isIntegerValidAndPositive(userHostId)) {
			throw new IllegalArgumentsCustomException("El id del usuario host no es válido.");
		}

		String getPlanSubscriptionByUserHostIdQuery = "SELECT psm " + "FROM PlanSubscriptionModel psm "
				+ "WHERE psm.planSubscriptionUserHostId.idUserHost = :userHostId";

		TypedQuery<PlanSubscriptionModel> planSubscriptionToReturn = em
				.createQuery(getPlanSubscriptionByUserHostIdQuery, PlanSubscriptionModel.class);

		planSubscriptionToReturn.setParameter("userHostId", userHostId);

		return planSubscriptionToReturn.getSingleResult();
	}

	@Override
	public List<PlanSubscriptionModel> findAllByPlanId(final Integer planId) {
		if (!isIntegerValidAndPositive(planId)) {
			throw new IllegalArgumentsCustomException("El id del plan [ " + planId + " ] no es válido.");
		}

		String getPlanSubscriptionByUserHostIdQuery = "SELECT psm " + "FROM PlanSubscriptionModel psm "
				+ "WHERE psm.planSubscriptionUserHostId.idPlan = :idPlan";

		TypedQuery<PlanSubscriptionModel> planSubscriptionToReturn = em
				.createQuery(getPlanSubscriptionByUserHostIdQuery, PlanSubscriptionModel.class);

		planSubscriptionToReturn.setParameter("idPlan", planId);

		return planSubscriptionToReturn.getResultList();
	}

}
