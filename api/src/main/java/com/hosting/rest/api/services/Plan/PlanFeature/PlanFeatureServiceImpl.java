package com.hosting.rest.api.services.Plan.PlanFeature;

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

import com.hosting.rest.api.models.Plan.PlanFeatureAppPlan.PlanFeatureModel;
import com.hosting.rest.api.repositories.Plan.IPlanRepository;
import com.hosting.rest.api.repositories.Plan.PlanFeature.IPlanFeatureRepository;

/**
 * 
 * @author Francisco Coya Abajo
 * @version v1.0.3
 * @apiNote Controlador para gestionar las acciones de las carácterísticas de un
 *          plan de subscripción.
 * 
 */
@Service
public class PlanFeatureServiceImpl implements IPlanFeatureService {

	@PersistenceContext
	private EntityManager em;

	@Autowired
	private IPlanFeatureRepository planFeatureRepo;

	@Autowired
	private IPlanRepository planRepo;

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
		// Validar característica del plan pasada como parametro
		validateParam(isNotNull(planFeatureToAdd), "La característica para el plan a añadir no es válida.");

		// Comprobar si existe la característica
		validateParamNotFound(!planFeatureRepo.existsById(planFeatureToAdd.getIdPlanFeature()),
				"Ya existe una característica.");

		return planFeatureRepo.save(planFeatureToAdd);
	}

	/**
	 * Borrado de una característica de un plan por el id <code>planFeatureId</code>
	 * pasado como parámetro.
	 * 
	 * @param planFeatureId
	 * 
	 * @throws NumberFormatException Si el id de la característica del plan no es un
	 *                               número.
	 */
	@Override
	public void deleteById(final Integer planFeatureId) throws NumberFormatException {
		// Validar id de la caracteristica del plan
		validateParam(isIntegerValidAndPositive(planFeatureId),
				"El id [ " + planFeatureId + " ] a eliminar no es válido.");

		// Comprobar si existe la característica
		validateParamNotFound(planFeatureRepo.existsById(planFeatureId),
				"La característica con id " + planFeatureId + " a eliminar no existe");

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
	public void updatePlanFeature(final Integer planFeatureId, final PlanFeatureModel planFeatureToUpdate)
			throws NumberFormatException {
		// Validar id de la caraterística
		validateParam(isIntegerValidAndPositive(planFeatureId),
				"El id [ " + planFeatureId + " ] de la característica a actualizar no es válido.");

		// Validar caracterísitca del plan pasada como parametro.
		validateParam(isNotNull(planFeatureToUpdate),
				"Alguno de los valores de la característica a actualizar no es válido.");

		// Comprobar si existe la característica
		validateParamNotFound(planFeatureRepo.existsById(planFeatureId), "La característica a actualizar no existe.");

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
	 * 
	 * @throws NumberFormatException Si el id del plan no es un número.
	 */
	@Override
	public List<PlanFeatureModel> findAllByPlanId(final Integer planId) throws NumberFormatException {
		// Validar id del plan
		validateParam(isIntegerValidAndPositive(planId),
				"El id [ " + planId + " ] del plan a listar sus características no es válido.");

		// Comprobar si existe el plan
		validateParamNotFound(planRepo.existsById(planId), "No existe un plan con id " + planId);

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
