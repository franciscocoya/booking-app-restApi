package com.hosting.rest.api.services.Accomodation.AccomodationService;

import static com.hosting.rest.api.Utils.AppUtils.isIntegerValidAndPositive;
import static com.hosting.rest.api.Utils.AppUtils.isNotNull;
import static com.hosting.rest.api.Utils.AppUtils.isStringNotBlank;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hosting.rest.api.exceptions.IllegalArguments.IllegalArgumentsCustomException;
import com.hosting.rest.api.exceptions.NotFound.NotFoundCustomException;
import com.hosting.rest.api.models.Accomodation.AccomodationService.AccomodationServiceModel;
import com.hosting.rest.api.repositories.Accomodation.IAccomodationRepository;
import com.hosting.rest.api.repositories.Accomodation.AccomodationService.IAccomodationServiceRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author Francisco Coya · https://github.com/FranciscoCoya
 * @version v1.0.2
 * @description Implementa las acciones relacionadas con los servicios que
 *              ofrece un alojamiento.
 * 
 **/
@Service
@Slf4j
public class AccomodationServiceServiceImpl implements IAccomodationServiceService {

	@PersistenceContext
	private EntityManager em;

	@Autowired
	private IAccomodationServiceRepository accomodationServiceRepo;

	@Autowired
	private IAccomodationRepository accomodationRepo;

	/**
	 * Añade un nuevo servicio.
	 * 
	 * @param accomodationService
	 * 
	 * @return
	 */
	@Override
	public AccomodationServiceModel addNewAccomodationService(final AccomodationServiceModel accomodationService) {
		if (!isNotNull(accomodationService)) {
			log.error("Alguno de los datos introducidos para el servicion del alojamiento no es válido.");
			throw new IllegalArgumentsCustomException(
					"Alguno de los datos introducidos para el servicion del alojamiento no es válido.");
		}

		return accomodationServiceRepo.save(accomodationService);
	}

	/**
	 * Actualización de los datos de un servicio de un alojamiento.
	 * 
	 * @param accomodationServiceId
	 * @param accomodationService
	 * 
	 * @return
	 * 
	 * @throws NumberFormatException Si el id del servicio del alojamiento no es un
	 *                               número.
	 */
	@Override
	public AccomodationServiceModel updateAccomodationService(final Integer accomodationServiceId,
			final AccomodationServiceModel accomodationService) throws NumberFormatException {
		if (!isIntegerValidAndPositive(accomodationServiceId)) {
			log.error("El id del servicio de alojamiento " + accomodationServiceId + " no es válido.");
			throw new IllegalArgumentsCustomException(
					"El id del servicio de alojamiento " + accomodationServiceId + " no es válido.");
		}

		if (!isNotNull(accomodationService)) {
			log.error("Alguno de los datos del servicio de alojamiento introducido no es válido.");
			throw new IllegalArgumentsCustomException(
					"Alguno de los datos del servicio de alojamiento introducido no es válido.");
		}

		// Comprobar si existe el servicio
		if (!accomodationServiceRepo.existsById(accomodationServiceId)) {
			log.error("El servicio de alojamiento a actualizar no existe.");
			throw new NotFoundCustomException("El servicio de alojamiento a actualizar no existe.");
		}

		// TODO: COMPLETAR
		return null;
	}

	/**
	 * Listado de todos los servicios ofrecidos por el alojamiento con número de
	 * registro <code>regNumber</code>.
	 * 
	 * @param regNumber
	 * 
	 * @return
	 */
	@Override
	public List<AccomodationServiceModel> findAllAccomodationServicesFromAccomodation(final String regNumber) {

		if (!isStringNotBlank(regNumber)) {
			log.error("El número de registro [ " + regNumber + " ] no es válido.");
			throw new IllegalArgumentsCustomException("El número de registro [ " + regNumber + " ] no es válido.");
		}

		// Comprobar si existe el alojamiento
		if (!accomodationRepo.existsById(regNumber)) {
			log.error("No existe un alojamiento con número de registro " + regNumber);
			throw new NotFoundCustomException("No existe un alojamiento con número de registro " + regNumber);
		}

		String findAllAccomodationServicesByRegisterNumberQuery = "SELECT asm "
				+ "FROM AccomodationServiceModel asm, AccomodationAccServiceModel accs INNER JOIN accs.accomodationAccServiceId acs "
				+ "WHERE acs.idAccomodation = :regNumber AND asm.id = acs.idAccomodationService";

		TypedQuery<AccomodationServiceModel> accomodationServices = em
				.createQuery(findAllAccomodationServicesByRegisterNumberQuery, AccomodationServiceModel.class);

		accomodationServices.setParameter("regNumber", regNumber);

		return accomodationServices.getResultList();
	}

	/**
	 * Borrado del servicio de alojamiento con id
	 * <code>accomodationServiceId</code>.
	 * 
	 * @param accomodationServiceId
	 * 
	 * @throws NumberFormatException Si el id del servicio del alojamiento no es un
	 *                               número.
	 */
	@Override
	@Transactional
	public void deleteAccomodationServiceById(final Integer accomodationServiceId) throws NumberFormatException {
		if (!isIntegerValidAndPositive(accomodationServiceId)) {
			throw new IllegalArgumentsCustomException(
					"El id del servicio [ " + accomodationServiceId + " ] no es válido.");
		}
		
		// Comprobar si existe el servicio
		if (!accomodationServiceRepo.existsById(accomodationServiceId)) {
			log.error("El servicio de alojamiento a eliminar no existe.");
			throw new NotFoundCustomException("El servicio de alojamiento a eliminar no existe.");
		}

		accomodationServiceRepo.deleteById(accomodationServiceId);
	}

	/**
	 * Obtención del servicio de alojamiento con id
	 * <code>accomodationServiceId</code>.
	 * 
	 * @param accomodationServiceId
	 * 
	 * @return
	 * 
	 * @throws NumberFormatException Si el id del servicio del alojamiento no es un
	 *                               número.
	 */
	@Override
	public AccomodationServiceModel getAccomodationServiceById(final Integer accomodationServiceId) throws NumberFormatException{
		if (!isIntegerValidAndPositive(accomodationServiceId)) {
			log.error("El id del servicio del alojamiento [ " + accomodationServiceId + " ] no es válido.");
			throw new IllegalArgumentsCustomException(
					"El id del servicio del alojamiento [ " + accomodationServiceId + " ] no es válido.");
		}

		return accomodationServiceRepo.findById(accomodationServiceId).get();
	}
}
