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

import com.hosting.rest.api.exceptions.IllegalArguments.IllegalArgumentsCustomException;
import com.hosting.rest.api.exceptions.NotFound.NotFoundCustomException;
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
			throw new IllegalArgumentsCustomException(
					"Los datos introducidos para el alojamiento no son válidos o falta alguna propiedad.");
		}

		// Comprobación del número de registro del alojamiento a registrar
		boolean existsAccomodation = accomodationRepo.existsById(accomodationModel.getRegisterNumber());

		if (existsAccomodation) {
			throw new IllegalArgumentsCustomException(
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
		return accomodationRepo.findById(regNumber).orElseThrow(() -> new NotFoundCustomException(regNumber));
	}

	@Override
	public String removeAccomodationById(final String regNumber) {
		if (!isStringNotBlank(regNumber)) {
			throw new IllegalArgumentsCustomException("El número de registro pasado como parámetro está vacío.");
		}

		boolean existsAccomodation = accomodationRepo.existsById(regNumber);

		if (!existsAccomodation) {
			throw new NotFoundCustomException(
					"El alojamiento con número de registro [ " + regNumber + " ] no existe.");
		}

		accomodationRepo.deleteById(regNumber);

		return "Alojamiento con número de registro [ " + regNumber + " ] eliminado correctamente";
	}

	@Override
	public List<AccomodationModel> findByCity(final String cityToSearch) {
		if (!isStringNotBlank(cityToSearch)) {
			throw new IllegalArgumentsCustomException(
					"El valor [ " + cityToSearch + " ] está vacío o no es válido.");
		}

		/**
		 * Listado de los alojamientos de la ciudad <code>cityToSearch</code>.
		 */
		String listAccomodationsByCityQuery = "SELECT ac "
				+ "FROM AccomodationModel ac INNER JOIN ac.idAccomodationLocation al " + "WHERE al.city = :city";

		TypedQuery<AccomodationModel> accomodations = em.createQuery(listAccomodationsByCityQuery,
				AccomodationModel.class);

		accomodations.setParameter("city", cityToSearch);

		return accomodations != null ? accomodations.getResultList() : null;
	}

	@Override
	public List<AccomodationModel> findByNearby(final BigDecimal lat, final BigDecimal lng, final double distance) {

		if (!isValidGeographicCoordinate(lat, true)) {
			throw new IllegalArgumentsCustomException("La latitud introducida no es válida.");
		}

		if (!isValidGeographicCoordinate(lng, false)) {
			throw new IllegalArgumentsCustomException("La longitud introducida no es válida.");
		}

		if (!isDoubleValidAndPositive(distance)) {
			throw new IllegalArgumentsCustomException("La distancia introducida no es válida.");
		}

		/**
		 * Listar los alojamientos en un radio de <code>distance</code>
		 */
		// + " and acloc.latitude = :latitude and acloc.longitude = :longitude"
		String findByNearbyLocationQuery = "SELECT am "
				+ "FROM AccomodationModel am INNER JOIN am.idAccomodationLocation acloc " + "WHERE " + HAVERSINE_FORMULA
				+ " < :distance" + " ORDER BY " + HAVERSINE_FORMULA + " DESC";

		TypedQuery<AccomodationModel> accomodations = em.createQuery(findByNearbyLocationQuery,
				AccomodationModel.class);

		accomodations.setParameter("latitude", lat);
		accomodations.setParameter("longitude", lng);
		accomodations.setParameter("distance", distance);

		return accomodations.setMaxResults(ACCOMODATION_LIMIT_RESULTS).getResultList();
	}

	@Override
	@Modifying
	public AccomodationModel updateAccomodationById(final String regNumber,
			final AccomodationModel accomodationToUpdate) {

		if (!isStringNotBlank(regNumber)) {
			throw new NotFoundCustomException(
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
			throw new IllegalArgumentsCustomException("La categoría introducida está vacía o no es válida.");
		}

		String findByAccomodationCategoryQuery = "SELECT am "
				+ "FROM AccomodationModel am INNER JOIN am.idAccomodationCategory acc "
				+ "WHERE acc.accomodationCategory = :category";

		TypedQuery<AccomodationModel> accomodations = em.createQuery(findByAccomodationCategoryQuery,
				AccomodationModel.class);

		accomodations.setParameter("category", accomodationCategory);

		return accomodations.getResultList();
	}

	@Override
	public List<AccomodationModel> findByPriceRange(final BigDecimal minPrice, final BigDecimal maxPrice) {
		if (!isBigDecimalValid(minPrice)) {
			throw new IllegalArgumentsCustomException("El precio mínimo introducido no es válido.");
		}

		if (!isBigDecimalValid(maxPrice)) {
			throw new IllegalArgumentsCustomException("El precio máximo introducido no es válido.");
		}

		String findByAccomodationCategoryQuery = "SELECT am " + "FROM AccomodationModel am "
				+ "WHERE am.pricePerNight BETWEEN :minPrice and :maxPrice " + "ORDER BY am.pricePerNight DESC";

		TypedQuery<AccomodationModel> accomodations = em.createQuery(findByAccomodationCategoryQuery,
				AccomodationModel.class);

		accomodations.setParameter("minPrice", minPrice);
		accomodations.setParameter("maxPrice", maxPrice);

		return accomodations.getResultList();
	}

}
