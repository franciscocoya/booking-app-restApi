package com.hosting.rest.api.services.Accomodation.AccomodationAccService;

import static com.hosting.rest.api.Utils.AppUtils.isIntegerValidAndPositive;
import static com.hosting.rest.api.Utils.AppUtils.isStringNotBlank;
import static com.hosting.rest.api.Utils.ServiceParamValidator.validateParam;
import static com.hosting.rest.api.Utils.ServiceParamValidator.validateParamNotFound;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hosting.rest.api.exceptions.IllegalArguments.IllegalArgumentsCustomException;
import com.hosting.rest.api.models.Accomodation.AccomodationService.AccomodationAccServiceId;
import com.hosting.rest.api.models.Accomodation.AccomodationService.AccomodationAccServiceModel;
import com.hosting.rest.api.models.Accomodation.AccomodationService.AccomodationServiceModel;
import com.hosting.rest.api.repositories.Accomodation.IAccomodationRepository;
import com.hosting.rest.api.repositories.Accomodation.AccomodationService.IAccomodationAccServiceRepo;
import com.hosting.rest.api.repositories.Accomodation.AccomodationService.IAccomodationServiceRepository;

/**
 * 
 * @author Francisco Coya · https://github.com/FranciscoCoya
 * @version v1.0.3
 * @description Implementa las acciones relacionadas con servicios que poseen
 *              los alojamientos. Relación alojamiento - servicio.
 * 
 **/
@Service
public class AccomodationAccServiceServiceImpl implements IAccomodationAccServiceService {

	@PersistenceContext
	private EntityManager em;

	@Autowired
	private IAccomodationAccServiceRepo accomodationAccServiceRepo;

	@Autowired
	private IAccomodationRepository accomodationRepo;

	@Autowired
	private IAccomodationServiceRepository accomodationServiceRepo;

	/**
	 * Añadir el servicio <code>accomodationServiceToAdd</code> a los servicios
	 * ofrecidos por el alojamiento con número de registro <code>regNumber</code>.
	 * 
	 * @param regNumber
	 * @param serviceId
	 * 
	 * @return
	 */
	@Override
	@Transactional
	public AccomodationAccServiceModel addNewAccomodationServiceToAccomodation(final String regNumber,
			final Integer serviceId) {
		// Validar número de registro del alojamiento
		validateParam(isStringNotBlank(regNumber), "El número de registro de alojamiento está vacío");

		// Validar id del servicio
		validateParam(isIntegerValidAndPositive(serviceId), "El id del servicio a añadir no es válido.");

		// Comprobar si existe el servicio
		validateParamNotFound(accomodationServiceRepo.existsById(serviceId),
				"El servicio a añadir al alojamiento no existe.");

		// Comprobar si existe el alojamiento
		validateParamNotFound(accomodationRepo.existsById(regNumber),
				"No existe el alojamiento con número de registro " + regNumber);

		// Comprobar si el servicio está añadido al alojamiento
		validateParamNotFound(!accomodationAccServiceRepo
				.existsById(new AccomodationAccServiceId(regNumber, accomodationServiceRepo.getById(serviceId))), "El servicio ya se encuentra añadido al alojamiento.");

		AccomodationServiceModel serviceToAdd = accomodationServiceRepo.getById(serviceId);

		// Clave compuesta por el número de registro del alojamiento y el id del
		// servicio a añadir al alojamiento.
		AccomodationAccServiceId newAccomodationServiceId = new AccomodationAccServiceId(regNumber, serviceToAdd);

		return accomodationAccServiceRepo.save(new AccomodationAccServiceModel(newAccomodationServiceId));
	}

	/**
	 * Borrado del servicio con id <code>accomodationServiceId</code> del
	 * alojamiento con número de registro <code>accomodationRegNumber</code>.
	 * 
	 * @see #checkAccomodationAndServiceExists
	 * 
	 * @param accomodationServiceId
	 * @param accomodationRegNumber
	 * 
	 * @throws NumberFormatException Si el id del servicio del alojamiento no es un
	 *                               número.
	 */
	@Override
	@Transactional
	public void deleteServiceFromAccomodation(final Integer accomodationServiceId, final String accomodationRegNumber)
			throws NumberFormatException {

		checkAccomodationAndServiceExists(accomodationServiceId, accomodationRegNumber);

		String deleteAccServiceByRegNumberAndAccServiceId = "DELETE FROM AccomodationAccServiceModel accsm "
				+ "WHERE accsm.accomodationAccServiceId.idAccomodation = :regNumber "
				+ "AND accsm.accomodationAccServiceId.idAccomodationService.id = :accServiceId ";

		em.createQuery(deleteAccServiceByRegNumberAndAccServiceId).setParameter("regNumber", accomodationRegNumber)
				.setParameter("accServiceId", accomodationServiceId).executeUpdate();
	}

	/**
	 * Obtención del sercicio con id <code>accomodationServiceId</code> del
	 * alojamiento con número de registro <code>regNumber</code>.
	 * 
	 * @see #checkAccomodationAndServiceExists
	 * 
	 * @param accomodationServiceId
	 * @param regNumber
	 * 
	 * @return
	 * 
	 * @throws NumberFormatException Si el id del servicio del alojamiento no es un
	 *                               número.
	 */
	@Override
	public AccomodationAccServiceModel getAccomodationAccServiceById(final Integer accomodationServiceId,
			final String regNumber) throws NumberFormatException {

		AccomodationAccServiceModel accomodationServiceToReturn = null;

		checkAccomodationAndServiceExists(accomodationServiceId, regNumber);

		String getAccomodationAccServiceByRegNumberAndAccomodationServiceIdQuery = "SELECT accs "
				+ "FROM AccomodationAccServiceModel accs "
				+ "WHERE accs.accomodationAccServiceId.idAccomodation = :regNumber "
				+ "AND accs.accomodationAccServiceId.idAccomodationService = :accomodationServiceId";

		TypedQuery<AccomodationAccServiceModel> accomodationAccServiceToReturn = em.createQuery(
				getAccomodationAccServiceByRegNumberAndAccomodationServiceIdQuery, AccomodationAccServiceModel.class);

		accomodationAccServiceToReturn.setParameter("regNumber", regNumber);
		accomodationAccServiceToReturn.setParameter("accomodationServiceId", accomodationServiceId);

		try {
			accomodationServiceToReturn = accomodationAccServiceToReturn.getSingleResult();
		} catch (NoResultException nre) {
			throw new IllegalArgumentsCustomException("Error... " + nre.getMessage());
		}

		return accomodationServiceToReturn;
	}

	/**
	 * Método auxiliar que comprueba que los ids de alojamiento y servicio son
	 * válidos y existen.
	 * 
	 * @param accomodationServiceId
	 * @param accomodationRegNumber
	 * 
	 * @throws NumberFormatException Si el id del servicio del alojamiento no es un
	 *                               número.
	 */
	private void checkAccomodationAndServiceExists(final Integer accomodationServiceId,
			final String accomodationRegNumber) throws NumberFormatException {
		// Validar id servicio de alojamiento
		validateParam(isIntegerValidAndPositive(accomodationServiceId),
				"El id del servicio del alojamiento [ " + accomodationServiceId + " ] no es válido.");

		// Validar número de registro de alojamiento
		validateParam(isStringNotBlank(accomodationRegNumber), "El número de registro de alojamiento está vacío");

		// Comprobar si existe el servicio
		validateParamNotFound(accomodationServiceRepo.existsById(accomodationServiceId),
				"No existe un servicio de alojamiento con id " + accomodationServiceId);

		// Comprobar si existe el alojamiento
		validateParamNotFound(accomodationRepo.existsById(accomodationRegNumber),
				"No existe el alojamiento con número de registro " + accomodationRegNumber);
	}

}
