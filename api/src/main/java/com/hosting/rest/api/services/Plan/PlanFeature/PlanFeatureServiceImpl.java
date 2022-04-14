package com.hosting.rest.api.services.Plan.PlanFeature;

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
import com.hosting.rest.api.models.Plan.PlanFeatureAppPlan.PlanFeatureModel;
import com.hosting.rest.api.repositories.Plan.PlanFeature.IPlanFeatureRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author Francisco Coya Abajo
 * @version v1.0.1
 * @apiNote Controlador para gestionar las acciones de las carácterísticas de un
 *          plan de subscripción.
 * 
 */
@Service
@Slf4j
public class PlanFeatureServiceImpl implements IPlanFeatureService {

	@PersistenceContext
	private EntityManager em;

	@Autowired
	private IPlanFeatureRepository planFeatureRepo;

	/**
	 * Añade una característica a un plan con los datos pasados en
	 * <code>planFeatureToAdd</code>
	 * 
	 * @param planFeatureToAdd
	 * 
	 * @return
	 */
	@Override
	public PlanFeatureModel addNewPlanFeature(final PlanFeatureModel planFeatureToAdd) {
		if (!isNotNull(planFeatureToAdd)) {
			log.error("La característica para el plan a añadir no es válida.");
			throw new IllegalArgumentsCustomException("La característica para el plan a añadir no es válida.");
		}

		if (planFeatureRepo.existsById(planFeatureToAdd.getIdPlanFeature())) {
			log.error("Ya existe una característica.");
			throw new IllegalArgumentsCustomException("Ya existe una característica.");
		}

		return planFeatureRepo.save(planFeatureToAdd);
	}

	/**
	 * Borrado de una característica de un plan por el id <code>planFeatureId</code>
	 * pasado como parámetro.
	 * 
	 * @param planFeatureId
	 */
	@Override
	public void deleteById(final Integer planFeatureId) {
		if (!isIntegerValidAndPositive(planFeatureId)) {
			log.error("El id [ " + planFeatureId + " ] a eliminar no es válido.");
			throw new IllegalArgumentsCustomException("El id [ " + planFeatureId + " ] a eliminar no es válido.");
		}

		if (!planFeatureRepo.existsById(planFeatureId)) {
			log.error("La característica con id " + planFeatureId + " a eliminar no existe");
			throw new NotFoundCustomException("La característica con id " + planFeatureId + " a eliminar no existe");
		}

		planFeatureRepo.deleteById(planFeatureId);

	}

	/**
	 * Actualización de una característica de un plan por su id.
	 * 
	 * @param planFeatureId
	 * @param planFeatureToUpdate
	 */
	@Transactional
	@Override
	public void updatePlanFeature(final Integer planFeatureId, final PlanFeatureModel planFeatureToUpdate) {
		if (!isIntegerValidAndPositive(planFeatureId)) {
			log.error("El id [ " + planFeatureId + " ] de la característica a actualizar no es válido.");
			throw new IllegalArgumentsCustomException(
					"El id [ " + planFeatureId + " ] de la característica a actualizar no es válido.");
		}

		if (!isNotNull(planFeatureToUpdate)) {
			log.error("Alguno de los valores de la característica a actualizar no es válido.");
			throw new IllegalArgumentsCustomException(
					"Alguno de los valores de la característica a actualizar no es válido.");
		}

		// Comprobar si existe la característica
		if (!planFeatureRepo.existsById(planFeatureId)) {
			log.error("La característica a actualizar no existe.");
			throw new NotFoundCustomException("La característica a actualizar no existe.");
		}

		String updatePlanFeatureQuery = "UPDATE PlanFeatureModel pfm "
				+ "SET pfm.planFeatureDetail = :planFeatureDetail " + "WHERE pfm.idPlanFeature = :planFeatureId";

		Query updatedFeaturePlan = em.createQuery(updatePlanFeatureQuery);

		updatedFeaturePlan.setParameter("planFeatureId", planFeatureId);
		updatedFeaturePlan.setParameter("planFeatureDetail", planFeatureToUpdate.getPlanFeatureDetail());

		updatedFeaturePlan.executeUpdate();
	}

	/**
	 * Listado de todas las características de un plan de subscripción.
	 * 
	 * @param planId
	 * 
	 * @return
	 */
	@Override
	public List<PlanFeatureModel> findAllByPlanId(final Integer planId) {

		if (!isIntegerValidAndPositive(planId)) {
			log.error("El id [ " + planId + " ] del plan a listar sus características no es válido.");
			throw new IllegalArgumentsCustomException(
					"El id [ " + planId + " ] del plan a listar sus características no es válido.");
		}

		String findAllFeaturesPlanByPlanQuery = "SELECT pfm "
				+ "FROM PlanFeatureAppPlanModel pfappm, PlanFeatureModel pfm "
				+ "WHERE pfappm.planFeatureAppPlanId.idPlan = :planId "
				+ "AND pfappm.planFeatureAppPlanId.idPlanFeature = pfm.idPlanFeature";

		TypedQuery<PlanFeatureModel> planFeaturesList = em.createQuery(findAllFeaturesPlanByPlanQuery,
				PlanFeatureModel.class);

		planFeaturesList.setParameter("planId", planId);

		return planFeaturesList.getResultList();
	}

}
