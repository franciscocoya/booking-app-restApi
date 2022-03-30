package com.hosting.rest.api.services.Accomodation.AccomodationService;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
		return accomodationService != null ? accomodationServiceRepo.save(accomodationService) : null;
	}

	@Override
	public AccomodationServiceModel updateAccomodationService(final AccomodationServiceModel accomodationService) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AccomodationServiceModel> findAllAccomodationServicesFromAccomodation(final String regNumber)
			throws IllegalArgumentException {
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
	public void deleteAccomodationServiceById(final String accomodationRegNumber, final Integer accomodationServiceId) {

		// TODO: Control de parametros

		/**
		 * Eliminar un servicio de un alojamiento.
		 */
		String deleteAccServiceByRegNumberAndAccServiceId = "DELETE FROM AccomodationAccServiceModel accs"
				+ " WHERE accs.accomodationAccServiceId.idAccomodation = :regNumber"
				+ " AND accs.accomodationAccServiceId.idAccomodationService = :accServiceId";

		Query deletedAccomodationService = em.createQuery(deleteAccServiceByRegNumberAndAccServiceId);

		deletedAccomodationService.setParameter("regNumber", accomodationRegNumber);
		deletedAccomodationService.setParameter("accServiceId", accomodationServiceId);

		deletedAccomodationService.executeUpdate();
	}

}
