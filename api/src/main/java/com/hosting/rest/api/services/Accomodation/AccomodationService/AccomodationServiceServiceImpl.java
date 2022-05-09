package com.hosting.rest.api.services.Accomodation.AccomodationService;

import static com.hosting.rest.api.Utils.AppUtils.isIntegerValidAndPositive;
import static com.hosting.rest.api.Utils.AppUtils.isNotNull;
import static com.hosting.rest.api.Utils.AppUtils.isStringNotBlank;
import static com.hosting.rest.api.Utils.ServiceParamValidator.validateParam;
import static com.hosting.rest.api.Utils.ServiceParamValidator.validateParamNotFound;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hosting.rest.api.models.Accomodation.AccomodationService.AccomodationServiceModel;
import com.hosting.rest.api.repositories.Accomodation.IAccomodationRepository;
import com.hosting.rest.api.repositories.Accomodation.AccomodationService.IAccomodationServiceRepository;

/**
 * 
 * @author Francisco Coya · https://github.com/FranciscoCoya
 * @version v1.0.3
 * @description Implementa las acciones relacionadas con los servicios que
 *              ofrece un alojamiento.
 * 
 **/
@Service
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
		// Validar servicio de alojamiento
		validateParam(isNotNull(accomodationService),
				"Alguno de los datos introducidos para el servicion del alojamiento no es válido.");

		// Comprobar si existe el servicio
		validateParamNotFound(!accomodationServiceRepo.existsById(accomodationService.getId()),
				"El servicio del alojamiento ya existe.");

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
		// Validar id del servicio
		validateParam(isIntegerValidAndPositive(accomodationServiceId),
				"El id del servicio de alojamiento " + accomodationServiceId + " no es válido.");

		// Validar servicio pasado como parametro
		validateParam(isNotNull(accomodationService),
				"Alguno de los datos del servicio de alojamiento introducido no es válido.");

		// Comprobar si existe el servicio
		validateParamNotFound(accomodationServiceRepo.existsById(accomodationServiceId),
				"El servicio de alojamiento a actualizar no existe.");

		AccomodationServiceModel originalAccomodationService = accomodationServiceRepo.getById(accomodationServiceId);

		originalAccomodationService.setDenomination(accomodationService.getDenomination());

		return accomodationServiceRepo.save(originalAccomodationService);
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
		// Validar número de registro del alojamiento
		validateParam(isStringNotBlank(regNumber), "El número de registro [ " + regNumber + " ] no es válido.");

		// Comprobar si existe el alojamiento
		validateParamNotFound(accomodationRepo.existsById(regNumber),
				"No existe un alojamiento con número de registro " + regNumber);

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
		// Validar id del servicio de alojamiento
		validateParam(isIntegerValidAndPositive(accomodationServiceId),
				"El id del servicio [ " + accomodationServiceId + " ] no es válido.");

		// Comprobar si existe el servicio
		validateParamNotFound(accomodationServiceRepo.existsById(accomodationServiceId),
				"El servicio de alojamiento a eliminar no existe.");

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
	public AccomodationServiceModel getAccomodationServiceById(final Integer accomodationServiceId)
			throws NumberFormatException {
		// Validar el id del servicio pasado como parámetro.
		validateParam(isIntegerValidAndPositive(accomodationServiceId),
				"El id del servicio del alojamiento [ " + accomodationServiceId + " ] no es válido.");

		return accomodationServiceRepo.findById(accomodationServiceId).get();
	}

	/**
	 * Listado de todos los servicios disponibles en la aplicación.
	 */
	@Override
	public List<AccomodationServiceModel> findAllAvailableAccomodations() {
		return accomodationServiceRepo.findAll();
	}
}
