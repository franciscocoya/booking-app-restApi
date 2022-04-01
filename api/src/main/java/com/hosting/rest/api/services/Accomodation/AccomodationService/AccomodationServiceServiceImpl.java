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
import com.hosting.rest.api.repositories.Accomodation.AccomodationService.IAccomodationServiceRepository;

@Service
public class AccomodationServiceServiceImpl implements IAccomodationServiceService {

	@PersistenceContext
	private EntityManager em;

	@Autowired
	private IAccomodationServiceRepository accomodationServiceRepo;

	@Override
	public AccomodationServiceModel addNewAccomodationService(final AccomodationServiceModel accomodationService) {
		if (!isNotNull(accomodationService)) {
			throw new IllegalArgumentsCustomException(
					"Alguno de los datos introducidos para el servicion del alojamiento no es válido.");
		}

		return accomodationServiceRepo.save(accomodationService);
	}

	@Override
	public AccomodationServiceModel updateAccomodationService(final AccomodationServiceModel accomodationService) {

		if (accomodationService == null) {
			throw new IllegalArgumentsCustomException(
					"Alguno de los datos del servicio de alojamiento introducido no es válido.");
		}

		boolean existsAccomodationService = false;

		if (!existsAccomodationService) {
			throw new NotFoundCustomException("El servicio de alojamiento a actualizar no existe.");
		}

		return null;
	}

	@Override
	public List<AccomodationServiceModel> findAllAccomodationServicesFromAccomodation(final String regNumber)
			throws IllegalArgumentException {

		if (!isStringNotBlank(regNumber)) {
			throw new IllegalArgumentsCustomException("El número de registro [ " + regNumber + " ] no es válido.");
		}

		/**
		 * Listado de todos los servicios que ofrece un alojamiento, el número de
		 * registro es pasado como parámetro: <code>regNumber</code>.
		 */
		String findAllAccomodationServicesByRegisterNumberQuery = "SELECT asm "
				+ "FROM AccomodationServiceModel asm, AccomodationAccServiceModel accs INNER JOIN accs.accomodationAccServiceId acs "
				+ "WHERE acs.idAccomodation = :regNumber AND asm.id = acs.idAccomodationService";

		TypedQuery<AccomodationServiceModel> accomodationServices = em
				.createQuery(findAllAccomodationServicesByRegisterNumberQuery, AccomodationServiceModel.class);

		accomodationServices.setParameter("regNumber", regNumber);

		return accomodationServices.getResultList();
	}

	@Override
	@Transactional
	public void deleteAccomodationServiceById(final Integer accomodationServiceId) {
		if (!isIntegerValidAndPositive(accomodationServiceId)) {
			throw new IllegalArgumentsCustomException(
					"El id del servicio [ " + accomodationServiceId + " ] no es válido.");
		}

		accomodationServiceRepo.deleteById(accomodationServiceId);
	}

	@Override
	public AccomodationServiceModel getAccomodationServiceById(final Integer accomodationServiceId) {
		if (!isIntegerValidAndPositive(accomodationServiceId)) {
			throw new IllegalArgumentsCustomException(
					"El id del servicio del alojamiento [ " + accomodationServiceId + " ] no es válido.");
		}

		return accomodationServiceRepo.findById(accomodationServiceId).get();
	}
}
