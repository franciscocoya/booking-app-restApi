package com.hosting.rest.api.services.Accomodation;

import static com.hosting.rest.api.Utils.AppUtils.isDoubleValidAndPositive;
import static com.hosting.rest.api.Utils.AppUtils.isNotNull;
import static com.hosting.rest.api.Utils.AppUtils.isStringNotBlank;
import static com.hosting.rest.api.Utils.AppUtils.isValidGeographicCoordinate;
import static com.hosting.rest.api.Utils.AppUtils.isBigDecimalValid;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import com.hosting.rest.api.exceptions.Accomodation.IllegalArguments.IllegalAccomodationArgumentsException;
import com.hosting.rest.api.exceptions.Accomodation.NotFound.AccomodationNotFoundException;
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

		// Si el alojamiento es null o no se pasa el número de registro o el
		// propietario.
		if (!isNotNull(accomodationModel) || !isNotNull(accomodationModel.getRegisterNumber())
				|| !isNotNull(accomodationModel.getIdUserHost())) {
			throw new IllegalAccomodationArgumentsException(
					"Los datos introducidos para el alojamiento no son válidos o falta alguna propiedad.");
		}

		// Comprobación del número de registro del alojamiento a registrar
		boolean existsAccomodation = accomodationRepo.existsById(accomodationModel.getRegisterNumber());

		if (existsAccomodation) {
			throw new IllegalAccomodationArgumentsException(
					"Ya se encuentra registrado un alojamiento con número de registro ["
							+ accomodationModel.getRegisterNumber() + " ].");
		}

		return accomodationRepo.save(accomodationModel);
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
	public String removeAccomodationById(final String regNumber) {
		if (!isStringNotBlank(regNumber)) {
			throw new IllegalAccomodationArgumentsException("El número de registro pasado como parámetro está vacío.");
		}

		boolean existsAccomodation = accomodationRepo.existsById(regNumber);

		if (!existsAccomodation) {
			throw new AccomodationNotFoundException(
					"El alojamiento con número de registro [ " + regNumber + " ] no existe.");
		}

		accomodationRepo.deleteById(regNumber);

		return "Alojamiento con número de registro [ " + regNumber + " ] eliminado correctamente";
	}

	@Override
	public List<AccomodationModel> findByCity(final String cityToSearch) {
		if (!isStringNotBlank(cityToSearch)) {
			throw new IllegalAccomodationArgumentsException(
					"El valor [ " + cityToSearch + " ] está vacío o no es válido.");
		}

		/**
		 * Listado de los alojamientos de la ciudad <code>cityToSearch</code>.
		 */
		String listAccomodationsByCityQuery = "select ac from AccomodationModel ac inner join ac.idAccomodationLocation al where al.city = :city";

		TypedQuery<AccomodationModel> accomodations = em.createQuery(listAccomodationsByCityQuery,
				AccomodationModel.class);

		accomodations.setParameter("city", cityToSearch);

		return accomodations != null ? accomodations.getResultList() : null;
	}

	@Override
	public List<AccomodationModel> findByNearby(final BigDecimal lat, final BigDecimal lng, final double distance) {

		if (!isValidGeographicCoordinate(lat, true)) {
			throw new IllegalAccomodationArgumentsException("La latitud introducida no es válida.");
		}

		if (!isValidGeographicCoordinate(lng, false)) {
			throw new IllegalAccomodationArgumentsException("La longitud introducida no es válida.");
		}

		if (!isDoubleValidAndPositive(distance)) {
			throw new IllegalAccomodationArgumentsException("La distancia introducida no es válida.");
		}

		/**
		 * Listar los alojamientos en un radio de <code>distance</code>
		 */
		// + " and acloc.latitude = :latitude and acloc.longitude = :longitude"
		String findByNearbyLocationQuery = "select am"
				+ " from AccomodationModel am inner join am.idAccomodationLocation acloc" + " where "
				+ HAVERSINE_FORMULA + " < :distance" + " order by " + HAVERSINE_FORMULA + " desc";
		// + " limit " + ACCOMODATION_LIMIT_RESULTS

		TypedQuery<AccomodationModel> accomodations = em.createQuery(findByNearbyLocationQuery,
				AccomodationModel.class);

		accomodations.setParameter("latitude", lat);
		accomodations.setParameter("longitude", lng);
		accomodations.setParameter("distance", distance);

		return accomodations.getResultList();
	}

	@Override
	@Modifying
	public AccomodationModel updateAccomodationById(final String regNumber,
			final AccomodationModel accomodationToUpdate) {

		if (!isStringNotBlank(regNumber)) {
			throw new AccomodationNotFoundException(
					"No se encontró ningún alojamiento con el número de registro [ " + regNumber + " ]");
		}

		// Detalles del alojamiento original
		AccomodationModel originalAccomodation = getAccomodationById(regNumber);

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

		return accomodationRepo.save(originalAccomodation);
	}

	@Override
	public List<AccomodationModel> findByCategory(final String accomodationCategory) {

		if (!isStringNotBlank(accomodationCategory)) {
			throw new IllegalAccomodationArgumentsException("La categoría introducida está vacía o no es válida.");
		}

		String findByAccomodationCategoryQuery = "select am"
				+ " from AccomodationModel am inner join am.idAccomodationCategory acc"
				+ " where acc.accomodationCategory = :category";

		TypedQuery<AccomodationModel> accomodations = em.createQuery(findByAccomodationCategoryQuery,
				AccomodationModel.class);

		accomodations.setParameter("category", accomodationCategory);

		return accomodations.getResultList();
	}

	@Override
	public List<AccomodationModel> findByPriceRange(final BigDecimal minPrice, final BigDecimal maxPrice) {
		if (!isBigDecimalValid(minPrice)) {
			throw new IllegalAccomodationArgumentsException("El precio mínimo introducido no es válido.");
		}

		if (!isBigDecimalValid(maxPrice)) {
			throw new IllegalAccomodationArgumentsException("El precio máximo introducido no es válido.");
		}

		String findByAccomodationCategoryQuery = "select am" + " from AccomodationModel am"
				+ " where am.pricePerNight between :minPrice and :maxPrice" + " order by am.pricePerNight desc";

		TypedQuery<AccomodationModel> accomodations = em.createQuery(findByAccomodationCategoryQuery,
				AccomodationModel.class);

		accomodations.setParameter("minPrice", minPrice);
		accomodations.setParameter("maxPrice", maxPrice);

		return accomodations.getResultList();
	}

}
