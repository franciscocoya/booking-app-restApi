package com.hosting.rest.api.services.Accomodation;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import static com.hosting.rest.api.Utils.AppUtils.isStringNotBlank;
import com.hosting.rest.api.exceptions.Accomodation.AccomodationNotFoundException;
import com.hosting.rest.api.models.Accomodation.AccomodationModel;
import com.hosting.rest.api.repositories.Accomodation.IAccomodationRepository;

/**
 * @author Francisco Coya · https://github.com/FranciscoCoya
 * @version v1.0.0
 * @description Implementa los métodos de los servicios de un alojamiento.
 **/
@Service
public class AccomodationServiceImpl implements IAccomodationService {

	@PersistenceContext
	private EntityManager em;

	@Autowired
	private IAccomodationRepository accomodationRepo;

	@Override
	public AccomodationModel addNewAccomodation(final AccomodationModel accomodationModel) {
		return accomodationModel != null ? accomodationRepo.save(accomodationModel) : null;
	}

	@Override
	public List<AccomodationModel> findAllAccomodations() {
		return accomodationRepo.findAll();
	}

	@Override
	public AccomodationModel getAccomodationById(final String regNumber) {
		return accomodationRepo.findById(regNumber).orElseThrow(() -> new AccomodationNotFoundException(regNumber));
	}

	@Override
	public void removeAccomodationById(final String regNumber) {
		accomodationRepo.deleteById(regNumber);
	}

	@Override
	public List<AccomodationModel> findByCity(final String cityToSearch) {
		/**
		 * Listado de los alojamientos de la ciudad <code>cityToSearch</code>.
		 */
		String listAccomodationsByCityQuery = "select ac from AccomodationModel ac inner join ac.idAccomodationLocation al where al.city = :city";

		TypedQuery<AccomodationModel> accomodations = em.createQuery(listAccomodationsByCityQuery,
				AccomodationModel.class);

		accomodations.setParameter("city", cityToSearch);

		return accomodations.getResultList();
	}

	@Override
	public List<AccomodationModel> findByRadiusFromCoordinates(final double lat, final double lng,
			final double distance) {
		
		// TODO:

		TypedQuery<AccomodationModel> accomodations = em.createQuery(HAVERSINE_FORMULA, AccomodationModel.class);

		accomodations.setParameter("latitude", lat);
		accomodations.setParameter("longitude", lng);

		return accomodations.getResultList();
	}

	@Override
	@Modifying
	public AccomodationModel updateAccomodationById(final String regNumber,
			final AccomodationModel accomodationToUpdate) {

		// Detalles del alojamiento original
		AccomodationModel originalAccomodation = isStringNotBlank(regNumber) ? getAccomodationById(regNumber) : null;

		if (originalAccomodation != null) {
			// Número de habitaciones
			originalAccomodation.setNumOfBedRooms(accomodationToUpdate.getNumOfBedRooms());

			// Número de baños
			originalAccomodation.setNumOfBathRooms(accomodationToUpdate.getNumOfBathRooms());

			// Número de camas
			originalAccomodation.setNumOfBeds(accomodationToUpdate.getNumOfBeds());

			// Número de invitados
			originalAccomodation.setNumOfGuests(accomodationToUpdate.getNumOfGuests());

			// Precio por noche
			originalAccomodation.setPricePerNight(accomodationToUpdate.getPricePerNight());

			// Superficie de la vivienda
			originalAccomodation.setArea(accomodationToUpdate.getArea());

			// Propietario de la vivienda
			if (accomodationToUpdate.getIdUserHost() != null) {
				originalAccomodation.setIdUserHost(accomodationToUpdate.getIdUserHost());
			}
		}

		return originalAccomodation != null ? accomodationRepo.save(originalAccomodation) : null;
	}

}
