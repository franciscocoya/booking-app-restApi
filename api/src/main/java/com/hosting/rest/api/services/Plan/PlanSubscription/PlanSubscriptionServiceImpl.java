package com.hosting.rest.api.services.Plan.PlanSubscription;

import static com.hosting.rest.api.Utils.AppUtils.isIntegerValidAndPositive;
import static com.hosting.rest.api.Utils.AppUtils.isNotNull;
import static com.hosting.rest.api.Utils.ServiceParamValidator.validateParam;
import static com.hosting.rest.api.Utils.ServiceParamValidator.validateParamNotFound;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hosting.rest.api.models.Plan.PlanSubscriptionModel;
import com.hosting.rest.api.repositories.Plan.PlanSubscription.IPlanSubscriptionRepository;
import com.hosting.rest.api.repositories.User.UserHost.IUserHostRepository;

/**
 * 
 * @author Francisco Coya
 * @version v1.0.3
 * @apiNote Servicio que gestiona las subscripciones usuario - plan.
 *
 */
@Service
public class PlanSubscriptionServiceImpl implements IPlanSubscriptionService {

	@PersistenceContext
	private EntityManager em;

	@Autowired
	private IPlanSubscriptionRepository planSubscriptionRepo;

	@Autowired
	private IUserHostRepository userHostRepo;

	/**
	 * Creación de una subscripción de un usuario a un plan, los datos de la
	 * suscripción se pasan en <code>planSubscriptionToAdd</code>
	 * 
	 * @param planSubscriptionToAdd
	 */
	@Override
	public PlanSubscriptionModel addNewPlanSubscription(final PlanSubscriptionModel planSubscriptionToAdd) {
		// Validar subscripción pasada como parámetro.
		validateParam(isNotNull(planSubscriptionToAdd),
				"Alguno de los valores de la subscripción del plan no es válido.");

		Integer planSubscriptionUserHostId = planSubscriptionToAdd.getPlanSubscriptionUserHostId().getIdUserHost();

		// Sólo los usuarios host están suscritos a un plan
		validateParam(isUserHost(planSubscriptionUserHostId), "El usuario con id [ " + planSubscriptionUserHostId);

		// Comprobar que no existe el plan de subscriptción a añadir. Para ello,
		// un usuario no puede tener asociado más de un plan.
		validateParamNotFound(!existsPlanSubscriptionByUserHostId(planSubscriptionUserHostId),
				"El usuario [ " + planSubscriptionUserHostId + " ] ya tiene asociado un plan de subscripción.");

		return planSubscriptionRepo.save(planSubscriptionToAdd);
	}

	/**
	 * Comprueba que un usuario con id <code>userHostId</code> es un usuario host.
	 * 
	 * @param userHostId
	 * 
	 * @return
	 * 
	 * @throws NumberFormatException Si el id del usuario host no es un número.
	 */
	private boolean isUserHost(final Integer userHostId) throws NumberFormatException {

		validateParam(isIntegerValidAndPositive(userHostId),
				"El id del usuario host [ " + userHostId + " ] no es válido.");

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
	 * 
	 * @throws NumberFormatException Si el id del usuario host no es un número.
	 */
	private boolean existsPlanSubscriptionByUserHostId(final Integer userHostId) throws NumberFormatException {
		String existsPlanSubscriptionByUserHostIdQuery = "SELECT COUNT(psm) > 0 " + "FROM PlanSubscriptionModel psm "
				+ "WHERE psm.planSubscriptionUserHostId.idUserHost = :userHostId";

		TypedQuery<Boolean> existsSubscription = em.createQuery(existsPlanSubscriptionByUserHostIdQuery, Boolean.class);

		existsSubscription.setParameter("userHostId", userHostId);

		return existsSubscription.getSingleResult();
	}

	/**
	 * Borrado de una subscripción a un plan para el usuario <code>userHostId</code>
	 * 
	 * @param userHostId
	 * 
	 * @throws NumberFormatException Si el id del usuario host no es un número.
	 */
	@Transactional
	@Override
	public void deletePlanSubscriptionByUserHostId(final Integer userHostId) throws NumberFormatException {
		// Validar is del usuario host
		validateParam(isIntegerValidAndPositive(userHostId), "El id del usuario host no es válido.");

		// Comprobar si existe la subscripción pasada como parámetro.
		validateParamNotFound(existsPlanSubscriptionByUserHostId(userHostId), "La subscripción al plan ya existe.");

		String deletePlanSubscriptionByUserHostIdQuery = "DELETE FROM PlanSubscriptionModel psm "
				+ "WHERE psm.planSubscriptionUserHostId.idUserHost = :userHostId";

		Query deletedPlanSubscription = em.createQuery(deletePlanSubscriptionByUserHostIdQuery);

		deletedPlanSubscription.setParameter("userHostId", userHostId);

		deletedPlanSubscription.executeUpdate();
	}

	/**
	 * Obtención de la subscripción del usuario host con id <code>userHostId</code>.
	 * 
	 * @param userHostId
	 * 
	 * @return
	 * 
	 * @throws NumberFormatException Si el id del usuario host no es un número.
	 */
	@Override
	public PlanSubscriptionModel getPlanSubscriptionByUserHostId(final Integer userHostId)
			throws NumberFormatException {
		// Validar el id del usuario host
		validateParam(isIntegerValidAndPositive(userHostId), "El id del usuario host no es válido.");

		// Comprobar que el usuario host existe
		validateParamNotFound(userHostRepo.existsById(userHostId), "No existe un usuario con id " + userHostId);

		String getPlanSubscriptionByUserHostIdQuery = "SELECT psm " + "FROM PlanSubscriptionModel psm "
				+ "WHERE psm.planSubscriptionUserHostId.idUserHost = :userHostId";

		TypedQuery<PlanSubscriptionModel> planSubscriptionToReturn = em
				.createQuery(getPlanSubscriptionByUserHostIdQuery, PlanSubscriptionModel.class);

		planSubscriptionToReturn.setParameter("userHostId", userHostId);

		return planSubscriptionToReturn.getSingleResult();
	}

	/**
	 * Listado de los subscripciones a un determinado plan <code>planId</code>.
	 * 
	 * @param planId
	 * 
	 * @return
	 */
	@Override
	public List<PlanSubscriptionModel> findAllByPlanId(final Integer planId) throws NumberFormatException{
		// Validar id del plan
		validateParam(isIntegerValidAndPositive(planId), "El id del plan [ " + planId + " ] no es válido.");

		String getPlanSubscriptionByUserHostIdQuery = "SELECT psm " + "FROM PlanSubscriptionModel psm "
				+ "WHERE psm.planSubscriptionUserHostId.idPlan = :idPlan";

		TypedQuery<PlanSubscriptionModel> planSubscriptionToReturn = em
				.createQuery(getPlanSubscriptionByUserHostIdQuery, PlanSubscriptionModel.class);

		planSubscriptionToReturn.setParameter("idPlan", planId);

		return planSubscriptionToReturn.getResultList();
	}

}
