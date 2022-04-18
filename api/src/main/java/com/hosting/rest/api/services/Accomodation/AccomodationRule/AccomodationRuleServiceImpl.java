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
import com.hosting.rest.api.repositories.Accomodation.IAccomodationRepository;
import com.hosting.rest.api.repositories.Accomodation.AccomodationRule.IAccomodationRuleRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author Francisco Coya · https://github.com/FranciscoCoya
 * @version v1.0.2
 * @description Implementa las acciones relacionadas con las reglas de un
 *              alojamiento.
 * 
 **/
@Service
@Slf4j
public class AccomodationRuleServiceImpl implements IAccomodationRuleService {

	@PersistenceContext
	private EntityManager em;

	@Autowired
	private IAccomodationRuleRepository accomodationRuleRepo;

	@Autowired
	private IAccomodationRepository accomodationRepo;

	/**
	 * Añade un nueva norma para los alojamientos.
	 * 
	 * @param accomodationRuleToAdd
	 * 
	 * @return
	 */
	@Override
	public AccomodationRuleModel addNewAccomodationRule(final AccomodationRuleModel accomodationRuleToAdd) {
		if (!isNotNull(accomodationRuleToAdd)) {
			log.error("Alguno de los valores de la norma del alojamiento a añadir no es válido.");
			throw new IllegalArgumentsCustomException(
					"Alguno de los valores de la norma del alojamiento a añadir no es válido.");
		}

		// Comprobar si existe la norma.
		if (accomodationRuleRepo.existsById(accomodationRuleToAdd.getId())) {
			log.error("La norma del alojamiento " + accomodationRuleToAdd.getId() + " ya existe.");
			throw new IllegalArgumentsCustomException(
					"La norma del alojamiento " + accomodationRuleToAdd.getId() + " ya existe.");
		}

		return accomodationRuleRepo.save(accomodationRuleToAdd);
	}

	/**
	 * Actualiza los datos de una norma de un alojamiento.
	 * 
	 * @param accomodationRuleId
	 * @param accomodationRuleToAdd
	 * 
	 * @throws NumberFormatException Si el id de la norma del alojamiento no es un
	 *                               número.
	 */
	@Transactional
	@Override
	public void updateAccomodationRule(final Integer accomodationRuleId,
			final AccomodationRuleModel accomodationRuleToAdd) throws NumberFormatException {

		if (!isIntegerValidAndPositive(accomodationRuleId)) {
			log.error("El id de la norma del alojamiento [ " + accomodationRuleId + " ] no es válido.");
			throw new IllegalArgumentsCustomException(
					"El id de la norma del alojamiento [ " + accomodationRuleId + " ] no es válido.");
		}

		if (!isNotNull(accomodationRuleToAdd)) {
			log.error("Alguno de los valores de la norma del alojamiento a actualizar no es válida.");
			throw new IllegalArgumentsCustomException(
					"Alguno de los valores de la norma del alojamiento a actualizar no es válida.");
		}

		// Comprobar si la norma del alojamiento existe
		if (!accomodationRuleRepo.existsById(accomodationRuleId)) {
			log.error("La norma del alojamiento a actualizar no existe.");
			throw new NotFoundCustomException("La norma del alojamiento a actualizar no existe.");
		}

		String updateAccomodationRuleQuery = "UPDATE AccomodationRuleModel arm " + "SET arm.rule = :accomodationRule "
				+ "WHERE arm.id = :accomodationRuleId";

		Query accomodationRuleUpdated = em.createQuery(updateAccomodationRuleQuery);

		accomodationRuleUpdated.setParameter("accomodationRule", accomodationRuleToAdd.getRule());
		accomodationRuleUpdated.setParameter("accomodationRuleId", accomodationRuleId);

		accomodationRuleUpdated.executeUpdate();
	}

	/**
	 * Obtención de la norma de alojamiento con id <code>accomodationRuleId</code>.
	 * 
	 * @param accomodationRuleId
	 * 
	 * @return
	 * 
	 * @throws NumberFormatException Si el id de la norma del alojamiento no es un
	 *                               número.
	 */
	@Override
	public AccomodationRuleModel findById(final Integer accomodationRuleId) throws NumberFormatException {
		if (!isIntegerValidAndPositive(accomodationRuleId)) {
			throw new IllegalArgumentsCustomException(
					"EL id de la norma del alojamiento [ " + accomodationRuleId + " ] no es válido.");
		}

		return accomodationRuleRepo.findById(accomodationRuleId).get();
	}

	/**
	 * Borrado de la norma de alojamiento con id <code>accomodationRuleId</code>.
	 * 
	 * @param accomodationRuleId
	 * 
	 * @throws NumberFormatException Si el id de la norma del alojamiento no es un
	 *                               número.
	 */
	@Override
	public void deleteAccomodationRule(final Integer accomodationRuleId) {
		if (!isIntegerValidAndPositive(accomodationRuleId)) {
			throw new IllegalArgumentsCustomException(
					"EL id de la norma del alojamiento [ " + accomodationRuleId + " ] no es válido.");
		}

		// Comprobar si la norma del alojamiento existe
		if (!accomodationRuleRepo.existsById(accomodationRuleId)) {
			log.error("La norma del alojamiento a eliminar no existe.");
			throw new NotFoundCustomException("La norma del alojamiento a eliminar no existe.");
		}

		accomodationRuleRepo.deleteById(accomodationRuleId);

	}

	/**
	 * Listado de las normas que tiene el alojamiento con número de registro
	 * <code>regNumber</code>.
	 * 
	 * @param regNumber
	 * 
	 * @return
	 */
	@Override
	public List<AccomodationRuleModel> findByAccomodationRegNumber(final String regNumber) {
		if (!isStringNotBlank(regNumber)) {
			throw new IllegalArgumentsCustomException(
					"El número de registro [ " + regNumber + " ] está vacío o no es válido.");
		}

		// Comprobar si existe el alojamiento.
		if (!accomodationRepo.existsById(regNumber)) {
			log.error("No existe un alojamiento con número de registro " + regNumber);
			throw new NotFoundCustomException("No existe un alojamiento con número de registro " + regNumber);
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
