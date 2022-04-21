package com.hosting.rest.api.services.Plan;

import static com.hosting.rest.api.Utils.AppUtils.isBigDecimalValid;
import static com.hosting.rest.api.Utils.AppUtils.isIntegerValidAndPositive;
import static com.hosting.rest.api.Utils.AppUtils.isNotNull;
import static com.hosting.rest.api.Utils.ServiceParamValidator.validateParam;
import static com.hosting.rest.api.Utils.ServiceParamValidator.validateParamNotFound;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hosting.rest.api.models.Plan.PlanModel;
import com.hosting.rest.api.repositories.Plan.IPlanRepository;
import com.hosting.rest.api.repositories.User.IUserRepository;

/**
 * 
 * @author Francisco Coya
 * @version v1.0.3
 * @apiNote Servicio que gestiona los planes de subscripción.
 *
 */
@Service
public class PlanServiceImpl implements IPlanService {

	@PersistenceContext
	private EntityManager em;

	@Autowired
	private IPlanRepository planRepo;

	@Autowired
	private IUserRepository userRepo;

	/**
	 * Añade un nuevo tipo de plan.
	 * 
	 * @param planToAdd
	 * 
	 * @return
	 */
	@Override
	public PlanModel addNewPlan(final PlanModel planToAdd) {
		// Validar plan pasado como parámetro
		validateParam(isNotNull(planToAdd), "Los datos del plan a añadir no existen o están incompletos.");

		// Comprobar si existe el plan
		validateParamNotFound(!planRepo.existsById(planToAdd.getIdPlan()), "El plan a añadir ya existe.");

		return planRepo.save(planToAdd);
	}

	/**
	 * Actualización de los datos de un plan de subscripción.
	 * 
	 * @param planId
	 * @param newPrice
	 * 
	 * @throws NumberFormatException Si el id del plan no es un número.
	 * 
	 */
	@Transactional
	@Override
	public void udpatePlan(final Integer planId, final BigDecimal newPrice) throws NumberFormatException {
		// Validar el id del plan.
		validateParam(isIntegerValidAndPositive(planId), "El id del plan " + planId + " no es válido.");

		// Validar el precio
		validateParam(isBigDecimalValid(newPrice), "El precio del plan introducido no es válido.");

		// Comprobar si existe el plan
		validateParamNotFound(planRepo.existsById(planId), "No existe un plan con id [ " + planId + " ].");

		String updatePlanModelQuery = "UPDATE PlanModel pm SET pm.price = :planPrice WHERE pm.idPlan = :planId";

		em.createQuery(updatePlanModelQuery).setParameter("planPrice", newPrice).setParameter("planId", planId)
				.executeUpdate();
	}

	/**
	 * Borrado de un plan con el id <code>planId</code> pasado como parámetro.
	 * 
	 * @param planId
	 * 
	 * @throws NumberFormatException Si el id del plan no es válido.
	 */
	@Override
	public void deletePlanById(final Integer planId) throws NumberFormatException {
		// Validar el id del plan
		validateParam(isIntegerValidAndPositive(planId), "El id el plan [ " + planId + " ] a eliminar no es válido.");

		// Comprobar si existe el plans
		validateParamNotFound(planRepo.existsById(planId), "El plan con id " + planId + " no es válido.");

		planRepo.deleteById(planId);
	}

	/**
	 * Listado de todos los planes de subscripción disponibles.
	 */
	@Override
	public List<PlanModel> findAllPlans() {
		return planRepo.findAll();
	}

	/**
	 * Obtención de un plan por con id <code>planId</code>.
	 * 
	 * @param planId
	 * 
	 * @return
	 * 
	 * @throws NumberFormatException Si el id del plan no es un número.
	 */
	@Override
	public PlanModel getPlanById(final Integer planId) throws NumberFormatException {
		// Validar el id del plan
		validateParam(isIntegerValidAndPositive(planId), "El id el plan [ " + planId + " ] a obtener no es válido.");

		return planRepo.findById(planId).get();
	}

	/**
	 * Obtención del plan de subscripción del usuario con id <code>userId</code>
	 * pasado como parámetro.
	 * 
	 * @param userId
	 * 
	 * @return
	 */
	@Override
	public PlanModel findByUserId(final Integer userId) throws NumberFormatException {
		// Validar el id de usuario
		validateParam(isIntegerValidAndPositive(userId),
				"El id del usuario [ " + userId + " ] a obtener no es válido.");

		// Comprobar si existe el usuario
		validateParamNotFound(userRepo.existsById(userId), "El usuario con id " + userId + " no es existe.");

		String findCurrentUserPlanQuery = "SELECT pm "
				+ "FROM PlanSubscriptionModel psm INNER JOIN psm.planSubscriptionUserHostId s, PlanModel pm "
				+ "WHERE s.idUserHost = :userId AND pm.idPlan = s.idPlan";

		TypedQuery<PlanModel> currentPlan = em.createQuery(findCurrentUserPlanQuery, PlanModel.class);

		currentPlan.setParameter("userId", userId);

		return currentPlan.getSingleResult();
	}

}
