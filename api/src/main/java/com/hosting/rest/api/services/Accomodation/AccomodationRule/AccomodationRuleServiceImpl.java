package com.hosting.rest.api.services.Accomodation.AccomodationRule;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.hosting.rest.api.Utils.AppUtils.isNotNull;
import static com.hosting.rest.api.Utils.AppUtils.isIntegerValidAndPositive;
import static com.hosting.rest.api.Utils.AppUtils.isStringNotBlank;

import com.hosting.rest.api.exceptions.IllegalArguments.IllegalArgumentsCustomException;
import com.hosting.rest.api.exceptions.NotFound.NotFoundCustomException;
import com.hosting.rest.api.models.Accomodation.AccomodationRule.AccomodationRuleModel;
import com.hosting.rest.api.repositories.Accomodation.AccomodationRule.IAccomodationRuleRepository;

@Service
public class AccomodationRuleServiceImpl implements IAccomodationRuleService {

	@PersistenceContext
	private EntityManager em;

	@Autowired
	private IAccomodationRuleRepository accomodationRuleRepo;

	@Override
	public AccomodationRuleModel addNewAccomodationRule(final AccomodationRuleModel accomodationRuleToAdd) {
		if (!isNotNull(accomodationRuleToAdd)) {
			throw new IllegalArgumentsCustomException(
					"Alguno de los valores de la norma del alojamiento a añadir no es válido.");
		}

		// Comprobar que la norma a añadir no existe
		boolean existsAccomodationRule = accomodationRuleRepo.existsById(accomodationRuleToAdd.getId());

		if (existsAccomodationRule) {
			throw new IllegalArgumentsCustomException("La norma del alojamiento " + accomodationRuleToAdd.getId() + " ya existe.");
		}

		return accomodationRuleRepo.save(accomodationRuleToAdd);
	}

	@Transactional
	@Override
	public void updateAccomodationRule(final Integer accomodationRuleId,
			final AccomodationRuleModel accomodationRuleToAdd) {

		if (!isIntegerValidAndPositive(accomodationRuleId)) {
			throw new IllegalArgumentsCustomException(
					"El id de la norma del alojamiento [ " + accomodationRuleId + " ] no es válido.");
		}

		if (!isNotNull(accomodationRuleToAdd)) {
			throw new IllegalArgumentsCustomException(
					"Alguno de los valores de la norma del alojamiento a actualizar no es válida.");
		}

		// Comprobar si la norma del alojamiento existe
		boolean existsAccomodationRule = accomodationRuleRepo.existsById(accomodationRuleId);

		if (!existsAccomodationRule) {
			throw new NotFoundCustomException("La norma del alojamiento a actualizar no existe.");
		}

		String updateAccomodationRuleQuery = "UPDATE AccomodationRuleModel arm " + "SET arm.rule = :accomodationRule "
				+ "WHERE arm.id = :accomodationRuleId";

		Query accomodationRuleUpdated = em.createQuery(updateAccomodationRuleQuery);

		accomodationRuleUpdated.setParameter("accomodationRule", accomodationRuleToAdd.getRule());
		accomodationRuleUpdated.setParameter("accomodationRuleId", accomodationRuleId);

		accomodationRuleUpdated.executeUpdate();
	}

	@Override
	public AccomodationRuleModel findById(final Integer accomodationRuleId) {
		if (!isIntegerValidAndPositive(accomodationRuleId)) {
			throw new IllegalArgumentsCustomException(
					"EL id de la norma del alojamiento [ " + accomodationRuleId + " ] no es válido.");
		}

		return accomodationRuleRepo.findById(accomodationRuleId).get();
	}

	@Override
	public void deleteAccomodationRule(final Integer accomodationRuleId) {
		if (!isIntegerValidAndPositive(accomodationRuleId)) {
			throw new IllegalArgumentsCustomException(
					"EL id de la norma del alojamiento [ " + accomodationRuleId + " ] no es válido.");
		}

		// Comprobar si la norma del alojamiento existe
		boolean existsAccomodationRule = accomodationRuleRepo.existsById(accomodationRuleId);

		if (!existsAccomodationRule) {
			throw new NotFoundCustomException("La norma del alojamiento a eliminar no existe.");
		}

		accomodationRuleRepo.deleteById(accomodationRuleId);

	}

	@Override
	public List<AccomodationRuleModel> findByAccomodationRegNumber(final String regNumber) {
		if (!isStringNotBlank(regNumber)) {
			throw new IllegalArgumentsCustomException(
					"El número de registro [ " + regNumber + " ] está vacío o no es válido.");
		}

		String listAllAccomodationRulesQuery = "SELECT arm "
				+ "FROM AccomodationAccRuleModel aacrm, AccomodationRuleModel arm "
				+ "WHERE aacrm.accomodationAccRuleId.idAccomodation = :regNumber "
				+ "AND arm.id = aacrm.accomodationAccRuleId.idAccomodationRule";

		TypedQuery<AccomodationRuleModel> listAccomodationRules = em.createQuery(listAllAccomodationRulesQuery,
				AccomodationRuleModel.class);

		listAccomodationRules.setParameter("regNumber", regNumber);

		return listAccomodationRules.getResultList();
	}

}
