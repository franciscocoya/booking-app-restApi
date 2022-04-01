package com.hosting.rest.api.services.Accomodation.AccomodationAccService;

import static com.hosting.rest.api.Utils.AppUtils.isIntegerValidAndPositive;
import static com.hosting.rest.api.Utils.AppUtils.isNotNull;
import static com.hosting.rest.api.Utils.AppUtils.isStringNotBlank;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hosting.rest.api.exceptions.IllegalArguments.IllegalArgumentsCustomException;
import com.hosting.rest.api.exceptions.NotFound.NotFoundCustomException;
import com.hosting.rest.api.models.Accomodation.AccomodationService.AccomodationAccServiceModel;
import com.hosting.rest.api.models.Accomodation.AccomodationService.AccomodationServiceModel;
import com.hosting.rest.api.repositories.Accomodation.AccomodationService.IAccomodationAccServiceRepo;

@Service
public class AccomodationAccServiceServiceImpl implements IAccomodationAccServiceService {

	@PersistenceContext
	private EntityManager em;

	@Autowired
	private IAccomodationAccServiceRepo accomodationAccServiceRepo;

	@Override
	@Transactional
	public AccomodationAccServiceModel addNewAccomodationServiceToAccomodation(final String regNumber,
			final AccomodationServiceModel accomodationServiceToAdd) {

		if (!isNotNull(accomodationServiceToAdd)) {
			throw new IllegalArgumentsCustomException(
					"Alguno de los valores introducidos para el servicio del alojamiento no es válido.");
		}

		boolean existsService = getAccomodationAccServiceById(accomodationServiceToAdd.getId(), regNumber) != null;

		if (!existsService) {
			throw new NotFoundCustomException(
					"No se puede añadir el servicio indicado ya que no se encuentra disponible.");
		}

		String addNewAccomodationAccServiceQuery = "INSERT INTO ACCOMODATION_ACC_SERVICE( ID_ACC, ID_ACC_SERVICE ) VALUES(:accomodationId, :regNumber)";

		Query addNewServiceToAccomodation = em.createNativeQuery(addNewAccomodationAccServiceQuery);

		addNewServiceToAccomodation.setParameter("accomodationId", accomodationServiceToAdd.getId());
		addNewServiceToAccomodation.setParameter("regNumber", regNumber);

		addNewServiceToAccomodation.executeUpdate();

		return null;
//		return accomodationAccServiceRepo.save(accomodationAccServiceToAdd);
	}

	@Override
	public void deleteServiceFromAccomodation(final Integer accomodationServiceId, final String accomodationRegNumber) {
		if (!isStringNotBlank(accomodationRegNumber)) {
			throw new IllegalArgumentsCustomException(
					"El número de registro [ " + accomodationRegNumber + " ] no es válido.");
		}

		if (!isIntegerValidAndPositive(accomodationServiceId)) {
			throw new IllegalArgumentsCustomException(
					"El id del servicio [ " + accomodationServiceId + " ] no es válido.");
		}

		String deleteAccServiceByRegNumberAndAccServiceId = "DELETE FROM AccomodationAccServiceModel accsm"
				+ " WHERE accs.accomodationAccServiceId.idAccomodation = :regNumber"
				+ " AND accs.accomodationAccServiceId.idAccomodationService = :accServiceId";

		Query deletedAccomodationService = em.createQuery(deleteAccServiceByRegNumberAndAccServiceId);

		deletedAccomodationService.setParameter("regNumber", accomodationRegNumber);
		deletedAccomodationService.setParameter("accServiceId", accomodationServiceId);

		deletedAccomodationService.executeUpdate();

	}

	@Override
	public AccomodationAccServiceModel getAccomodationAccServiceById(final Integer accomodationServiceId,
			final String regNumber) {
		if (!isIntegerValidAndPositive(accomodationServiceId)) {
			throw new IllegalArgumentsCustomException(
					"El id del servicio del alojamiento [ " + accomodationServiceId + " ] no es válido.");
		}

		String getAccomodationAccServiceByRegNumberAndAccomodationServiceIdQuery = "SELECT accs FROM AccomodationAccService accs WHERE ";

		TypedQuery<AccomodationAccServiceModel> accomodationAccService = em.createQuery(
				getAccomodationAccServiceByRegNumberAndAccomodationServiceIdQuery, AccomodationAccServiceModel.class);

		accomodationAccService.setParameter("regNumber", regNumber);
		accomodationAccService.setParameter("accomodationServiceId", accomodationServiceId);

		return accomodationAccService.getSingleResult();

	}

}
