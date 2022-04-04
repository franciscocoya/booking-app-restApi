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

@Service
public class PlanFeatureServiceImpl implements IPlanFeatureService {

	@PersistenceContext
	private EntityManager em;

	@Autowired
	private IPlanFeatureRepository planFeatureRepo;

	@Override
	public PlanFeatureModel addNewPlanFeature(final PlanFeatureModel planFeatureToAdd) {
		if (!isNotNull(planFeatureToAdd)) {
			throw new IllegalArgumentsCustomException("La característica para el plan a añadir no es válida.");
		}

		boolean existsFeature = planFeatureRepo.existsById(planFeatureToAdd.getIdPlanFeature());

		if (existsFeature) {
			throw new IllegalArgumentsCustomException("Ya existe una característica.");
		}

		return planFeatureRepo.save(planFeatureToAdd);
	}

	@Override
	public void deleteById(final Integer planFeatureId) {
		if (!isIntegerValidAndPositive(planFeatureId)) {
			throw new IllegalArgumentsCustomException("El id [ " + planFeatureId + " ] a eliminar no es válido.");
		}

		boolean existsFeature = planFeatureRepo.existsById(planFeatureId);

		if (!existsFeature) {
			throw new NotFoundCustomException("La característica a eliminar no existe");
		}

		planFeatureRepo.deleteById(planFeatureId);

	}

	@Transactional
	@Override
	public void updatePlanFeature(final Integer planFeatureId, final PlanFeatureModel planFeatureToUpdate) {
		if (!isIntegerValidAndPositive(planFeatureId)) {
			throw new IllegalArgumentsCustomException(
					"El id [ " + planFeatureId + " ] de la característica a actualizar no es válido.");
		}

		if (!isNotNull(planFeatureToUpdate)) {
			throw new IllegalArgumentsCustomException(
					"Alguno de los valore de la característica a actualizar no es válido.");
		}

		boolean existsPlanFeature = planFeatureRepo.existsById(planFeatureId);

		if (!existsPlanFeature) {
			throw new NotFoundCustomException("La característica a actualizar no existe.");
		}

		String updatePlanFeatureQuery = "UPDATE PlanFeatureModel pfm SET pfm.planFeatureDetail = :planFeatureDetail "
				+ "WHERE pfm.idPlanFeature = :planFeatureId";

		Query updatedFeaturePlan = em.createQuery(updatePlanFeatureQuery);

		updatedFeaturePlan.setParameter("planFeatureId", planFeatureId);
		updatedFeaturePlan.setParameter("planFeatureDetail", planFeatureToUpdate.getPlanFeatureDetail());

		updatedFeaturePlan.executeUpdate();
	}

	@Override
	public List<PlanFeatureModel> findAllByPlanId(final Integer planId) {

		if (!isIntegerValidAndPositive(planId)) {
			throw new IllegalArgumentsCustomException(
					"El id [ " + planId + " ] del plan a listar sus características no es válido.");
		}

		String findAllFeaturesPlanByPlanQuery = "SELECT pfm "
				+ "FROM PlanFeatureAppPlanModel pfappm, PlanFeatureModel pfm "
				+ "WHERE pfappm.planFeatureAppPlanId.idPlan = :planId AND pfappm.planFeatureAppPlanId.idPlanFeature = pfm.idPlanFeature";

		TypedQuery<PlanFeatureModel> planFeaturesList = em.createQuery(findAllFeaturesPlanByPlanQuery,
				PlanFeatureModel.class);

		planFeaturesList.setParameter("planId", planId);

		return planFeaturesList.getResultList();
	}

}
