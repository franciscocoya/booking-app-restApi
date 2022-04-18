package com.hosting.rest.api.services.Accomodation.AccomodationAccService;

import static com.hosting.rest.api.Utils.AppUtils.isIntegerValidAndPositive;
import static com.hosting.rest.api.Utils.AppUtils.isNotNull;
import static com.hosting.rest.api.Utils.AppUtils.isStringNotBlank;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hosting.rest.api.exceptions.IllegalArguments.IllegalArgumentsCustomException;
import com.hosting.rest.api.exceptions.NotFound.NotFoundCustomException;
import com.hosting.rest.api.models.Accomodation.AccomodationService.AccomodationAccServiceId;
import com.hosting.rest.api.models.Accomodation.AccomodationService.AccomodationAccServiceModel;
import com.hosting.rest.api.models.Accomodation.AccomodationService.AccomodationServiceModel;
import com.hosting.rest.api.repositories.Accomodation.IAccomodationRepository;
import com.hosting.rest.api.repositories.Accomodation.AccomodationService.IAccomodationAccServiceRepo;
import com.hosting.rest.api.repositories.Accomodation.AccomodationService.IAccomodationServiceRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author Francisco Coya · https://github.com/FranciscoCoya
 * @version v1.0.2
 * @description Implementa las acciones relacionadas con servicios que poseen
 *              los alojamientos. Relación alojamiento - servicio.
 * 
 **/
@Service
@Slf4j
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
	 * @param accomodationServiceToAdd
	 * 
	 * @return
	 */
	@Override
	@Transactional
	public AccomodationAccServiceModel addNewAccomodationServiceToAccomodation(final String regNumber,
			final AccomodationServiceModel accomodationServiceToAdd) {

		if (!isNotNull(accomodationServiceToAdd)) {
			log.error("Alguno de los valores introducidos para el servicio del alojamiento no es válido.");
			throw new IllegalArgumentsCustomException(
					"Alguno de los valores introducidos para el servicio del alojamiento no es válido.");
		}

		if (!isStringNotBlank(regNumber)) {
			log.error("El número de registro de alojamiento está vacío");
			throw new IllegalArgumentsCustomException("El número de registro de alojamiento está vacío");
		}

		// Comprobar si existe el alojamiento
		if (!accomodationRepo.existsById(regNumber)) {
			log.error("No existe el alojamiento con número de registro " + regNumber);
			throw new NotFoundCustomException("No existe el alojamiento con número de registro " + regNumber);
		}

		// Clave compuesta por el número de registro del alojamiento y el id del
		// servicio a añadir al alojamiento.
		AccomodationAccServiceId newAccomodationServiceId = new AccomodationAccServiceId(regNumber,
				accomodationServiceToAdd.getId());

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
	public void deleteServiceFromAccomodation(final Integer accomodationServiceId, final String accomodationRegNumber)
			throws NumberFormatException {

		checkAccomodationAndServiceExists(accomodationServiceId, accomodationRegNumber);

		String deleteAccServiceByRegNumberAndAccServiceId = "DELETE FROM AccomodationAccServiceModel accsm "
				+ " WHERE accs.accomodationAccServiceId.idAccomodation = :regNumber "
				+ " AND accs.accomodationAccServiceId.idAccomodationService = :accServiceId ";

		Query deletedAccomodationService = em.createQuery(deleteAccServiceByRegNumberAndAccServiceId);

		deletedAccomodationService.setParameter("regNumber", accomodationRegNumber);
		deletedAccomodationService.setParameter("accServiceId", accomodationServiceId);

		deletedAccomodationService.executeUpdate();
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

		if (!isIntegerValidAndPositive(accomodationServiceId)) {
			log.error("El id del servicio del alojamiento [ " + accomodationServiceId + " ] no es válido.");
			throw new IllegalArgumentsCustomException(
					"El id del servicio del alojamiento [ " + accomodationServiceId + " ] no es válido.");
		}

		if (!isStringNotBlank(accomodationRegNumber)) {
			log.error("El número de registro de alojamiento está vacío");
			throw new IllegalArgumentsCustomException("El número de registro de alojamiento está vacío");
		}

		// Comprobar si existe el servicio
		if (!accomodationServiceRepo.existsById(accomodationServiceId)) {
			log.error("No existe un servicio de alojamiento con id " + accomodationServiceId);
			throw new IllegalArgumentsCustomException(
					"No existe un servicio de alojamiento con id " + accomodationServiceId);
		}

		// Comprobar si existe el alojamiento
		if (!accomodationRepo.existsById(accomodationRegNumber)) {
			log.error("No existe el alojamiento con número de registro " + accomodationRegNumber);
			throw new NotFoundCustomException(
					"No existe el alojamiento con número de registro " + accomodationRegNumber);
		}
	}

}
