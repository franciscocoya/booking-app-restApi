package com.hosting.rest.api.services.Accomodation.AccomodationRule;

import static com.hosting.rest.api.Utils.AppUtils.isIntegerValidAndPositive;
import static com.hosting.rest.api.Utils.AppUtils.isNotNull;
import static com.hosting.rest.api.Utils.AppUtils.isStringNotBlank;
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

import com.hosting.rest.api.models.Accomodation.AccomodationRule.AccomodationAccRuleId;
import com.hosting.rest.api.models.Accomodation.AccomodationRule.AccomodationAccRuleModel;
import com.hosting.rest.api.models.Accomodation.AccomodationRule.AccomodationRuleModel;
import com.hosting.rest.api.repositories.Accomodation.IAccomodationRepository;
import com.hosting.rest.api.repositories.Accomodation.AccomodationRule.IAccomodationAccRuleRepository;
import com.hosting.rest.api.repositories.Accomodation.AccomodationRule.IAccomodationRuleRepository;

/**
 * 
 * @author Francisco Coya · https://github.com/FranciscoCoya
 * @version v1.0.4
 * @description Implementa las acciones relacionadas con las reglas de un
 *              alojamiento.
 * 
 **/
@Service
public class AccomodationRuleServiceImpl implements IAccomodationRuleService {

	@PersistenceContext
	private EntityManager em;

	@Autowired
	private IAccomodationRuleRepository accomodationRuleRepo;

	@Autowired
	private IAccomodationRepository accomodationRepo;

	@Autowired
	private IAccomodationAccRuleRepository accomodationAccRuleRepo;

	/**
	 * Añade un nueva norma para los alojamientos.
	 * 
	 * @param accomodationRuleToAdd
	 * 
	 * @return
	 */
	@Override
	public AccomodationRuleModel addNewAccomodationRule(final AccomodationRuleModel accomodationRuleToAdd) {

		// Validar norma pasada como parametro
		validateParam(isNotNull(accomodationRuleToAdd),
				"Alguno de los valores de la norma del alojamiento a añadir no es válido.");

		// Comprobar si existe la norma.
		validateParamNotFound(!accomodationRuleRepo.existsById(accomodationRuleToAdd.getId()),
				"La norma del alojamiento " + accomodationRuleToAdd.getId() + " ya existe.");

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
// Validar id de la norma
		validateParam(isIntegerValidAndPositive(accomodationRuleId),
				"El id de la norma del alojamiento [ " + accomodationRuleId + " ] no es válido.");

		// Validar norma a actualizar
		validateParam(isNotNull(accomodationRuleToAdd),
				"Alguno de los valores de la norma del alojamiento a actualizar no es válida.");

		// Comprobar si la norma del alojamiento existe
		validateParamNotFound(accomodationRuleRepo.existsById(accomodationRuleId),
				"La norma del alojamiento a actualizar no existe.");

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
		// Id de la norma del alojamiento
		validateParam(isIntegerValidAndPositive(accomodationRuleId),
				"EL id de la norma del alojamiento [ " + accomodationRuleId + " ] no es válido.");

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
	public void deleteAccomodationRule(final Integer accomodationRuleId) throws NumberFormatException {
		// Valdiar id de la norma de alojamiento
		validateParam(isIntegerValidAndPositive(accomodationRuleId),
				"EL id de la norma del alojamiento [ " + accomodationRuleId + " ] no es válido.");

		// Comprobar si la norma del alojamiento existe
		validateParamNotFound(accomodationRuleRepo.existsById(accomodationRuleId),
				"La norma del alojamiento a eliminar no existe.");

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
		// Validar el número de registro de alojamiento
		validateParam(isStringNotBlank(regNumber),
				"El número de registro [ " + regNumber + " ] está vacío o no es válido.");

		// Comprobar si existe el alojamiento.
		validateParamNotFound(accomodationRepo.existsById(regNumber),
				"No existe un alojamiento con número de registro " + regNumber);

		String listAllAccomodationRulesQuery = "SELECT arm "
				+ "FROM AccomodationAccRuleModel aacrm, AccomodationRuleModel arm "
				+ "WHERE aacrm.accomodationAccRuleId.idAccomodation = :regNumber "
				+ "AND arm.id = aacrm.accomodationAccRuleId.idAccomodationRule";

		TypedQuery<AccomodationRuleModel> listAccomodationRules = em.createQuery(listAllAccomodationRulesQuery,
				AccomodationRuleModel.class);

		listAccomodationRules.setParameter("regNumber", regNumber);

		return listAccomodationRules.getResultList();
	}

	@Override
	public List<AccomodationRuleModel> findAllRules() {
		return accomodationRuleRepo.findAll();
	}

	@Transactional
	@Override
	public AccomodationAccRuleModel addNewAccomodationRuleToExistingAccomodation(final Integer accomodationRuleId,
			final String regNumber) {
		// Validar id de la norma de aloajiento
		validateParam(isIntegerValidAndPositive(accomodationRuleId),
				"El id de la norma del alojamiento a añadir no es válida.");

		// Comprobar si existe la norma
		validateParamNotFound(accomodationRuleRepo.existsById(accomodationRuleId),
				"No existe una norma con el id " + accomodationRuleId);

		// Validar número de registro del alojamiento
		validateParam(isStringNotBlank(regNumber), "El número de registro de alojamiento está vacío o no es válido.");

		// Comprobar si existe el alojamiento
		validateParamNotFound(accomodationRepo.existsById(regNumber),
				"No existe un alojamiento con número de registro " + regNumber);

		// Obtener la norma con id pasado como parámetro
		AccomodationRuleModel accomodationRuleToAdd = accomodationRuleRepo.findById(accomodationRuleId).get();

		// Añadir la norma al alojamiento
		AccomodationAccRuleId accomodationAccRuleToAdd = new AccomodationAccRuleId(regNumber, accomodationRuleToAdd);

		return accomodationAccRuleRepo.save(new AccomodationAccRuleModel(accomodationAccRuleToAdd));
	}

	/**
	 * Elimina la norma con id <code>accomdoationRuleId</code> del alojamiento con
	 * id <code>regNumber</code>.
	 * 
	 * @param accomodationRuleId
	 * @param regNumber
	 * 
	 */
	@Transactional
	@Override
	public void deleteAccomodationRuleFromAccomodation(final Integer accomodationRuleId, final String regNumber) {
		String deleteAccRuleByRegNumberAndAccRuleId = "DELETE FROM AccomodationAccRuleModel accrm "
				+ "WHERE accrm.accomodationAccRuleId.idAccomodation = :regNumber "
				+ "AND accrm.accomodationAccRuleId.idAccomodationRule.id = :accRuleId ";

		em.createQuery(deleteAccRuleByRegNumberAndAccRuleId).setParameter("regNumber", regNumber)
				.setParameter("accRuleId", accomodationRuleId).executeUpdate();
	}
}
