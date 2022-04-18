package com.hosting.rest.api.services.Plan;

import static com.hosting.rest.api.Utils.AppUtils.isBigDecimalValid;
import static com.hosting.rest.api.Utils.AppUtils.isIntegerValidAndPositive;
import static com.hosting.rest.api.Utils.AppUtils.isNotNull;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hosting.rest.api.exceptions.IllegalArguments.IllegalArgumentsCustomException;
import com.hosting.rest.api.exceptions.NotFound.NotFoundCustomException;
import com.hosting.rest.api.models.Plan.PlanModel;
import com.hosting.rest.api.repositories.Plan.IPlanRepository;
import com.hosting.rest.api.repositories.User.IUserRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author Francisco Coya
 * @version v1.0.1
 * @apiNote Servicio que gestiona los planes de subscripción.
 *
 */
@Slf4j
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
		if (!isNotNull(planToAdd)) {
			log.error("Los datos del plan a añadir no existen o están incompletos.");
			throw new IllegalArgumentsCustomException("Los datos del plan a añadir no existen o están incompletos.");
		}

		if (planRepo.existsById(planToAdd.getIdPlan())) {
			log.error("El plan a añadir ya existe.");
			throw new IllegalArgumentsCustomException("El plan a añadir ya existe.");
		}

		// TODO: Error java.lang.IllegalArgumentException: The given id must not be
		// null!

		return planRepo.save(planToAdd);
	}

	/**
	 * Actualización de los datos de un plan de subscripción.
	 * 
	 * @param planId
	 * @param newPrice
	 * 
	 */
	@Transactional
	@Override
	public void udpatePlan(final Integer planId, final BigDecimal newPrice) throws NumberFormatException {

		if (!isIntegerValidAndPositive(planId)) {
			throw new IllegalArgumentsCustomException("El id del plan " + planId + " no es válido.");
		}

		if (!isBigDecimalValid(newPrice)) {
			throw new IllegalArgumentsCustomException("El precio del plan introducido no es válido.");
		}

		if (!planRepo.existsById(planId)) {
			throw new NotFoundCustomException("No existe un plan con id [ " + planId + " ].");
		}

		String updatePlanModelQuery = "UPDATE PlanModel pm SET pm.price = :planPrice WHERE pm.idPlan = :planId";

		em.createQuery(updatePlanModelQuery).setParameter("planPrice", newPrice).setParameter("planId", planId)
				.executeUpdate();
	}

	/**
	 * Borrado de un plan con el id <code>planId</code> pasado como parámetro.
	 * 
	 * @param planId
	 */
	@Override
	public void deletePlanById(final Integer planId) throws NumberFormatException {

		if (!isIntegerValidAndPositive(planId)) {
			throw new IllegalArgumentsCustomException("El id el plan [ " + planId + " ] a eliminar no es válido.");
		}

		if (!planRepo.existsById(planId)) {
			log.error("El plan con id " + planId + " no es válido.");
			throw new NotFoundCustomException("El plan con id " + planId + " no es válido.");
		}

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
	 * Obtención de un plan por su id.
	 * 
	 * @param planId
	 * 
	 * @return
	 */
	@Override
	public PlanModel getPlanById(final Integer planId) throws NumberFormatException, IllegalArgumentException {
		if (!isIntegerValidAndPositive(planId)) {
			log.error("El id el plan [ " + planId + " ] a obtener no es válido.");
			throw new IllegalArgumentsCustomException("El id el plan [ " + planId + " ] a obtener no es válido.");
		}

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
	public PlanModel findByUserId(final Integer userId) throws IllegalArgumentException, NumberFormatException {

		if (!isIntegerValidAndPositive(userId)) {
			log.error("El id del usuario [ " + userId + " ] a obtener no es válido.");
			throw new IllegalArgumentsCustomException("El id del usuario [ " + userId + " ] a obtener no es válido.");
		}

		if (!userRepo.existsById(userId)) {
			log.error("El usuario con id " + userId + " no es existe.");
			throw new NotFoundCustomException("El usuario con id " + userId + " no es existe.");
		}

		String findCurrentUserPlanQuery = "SELECT pm "
				+ "FROM PlanSubscriptionModel psm INNER JOIN psm.planSubscriptionUserHostId s, PlanModel pm "
				+ "WHERE s.idUserHost = :userId AND pm.idPlan = s.idPlan";

		TypedQuery<PlanModel> currentPlan = em.createQuery(findCurrentUserPlanQuery, PlanModel.class);

		currentPlan.setParameter("userId", userId);

		return currentPlan.getSingleResult();
	}

}
