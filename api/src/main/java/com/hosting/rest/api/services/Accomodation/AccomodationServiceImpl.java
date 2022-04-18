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

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author Francisco Coya · https://github.com/FranciscoCoya
 * @version v1.0.3
 * @description Implementa las acciones relacionadas a los alojamientos.
 * 
 **/
@Service
@Slf4j
public class AccomodationServiceImpl implements IAccomodationService {

	@PersistenceContext
	private EntityManager em;

	@Autowired
	private IAccomodationRepository accomodationRepo;

	/**
	 * Registro de un nuevo alojamiento dentro de la aplicación.
	 * 
	 * @param accomodationModel
	 * 
	 * @return
	 */
	@Override
	public AccomodationModel addNewAccomodation(final AccomodationModel accomodationModel) {

		// Si el alojamiento es null o no se pasa el número de registro o el
		// propietario.
		if (!isNotNull(accomodationModel) || !isNotNull(accomodationModel.getRegisterNumber())
				|| !isNotNull(accomodationModel.getIdUserHost())) {
			log.error("Los datos introducidos para el alojamiento no son válidos o falta algún dato.");
			throw new IllegalArgumentsCustomException(
					"Los datos introducidos para el alojamiento no son válidos o falta algún dato.");
		}

		// Comprobar si existe el alojamiento
		if (accomodationRepo.existsById(accomodationModel.getRegisterNumber())) {
			log.error("Ya se encuentra registrado un alojamiento con número de registro ["
					+ accomodationModel.getRegisterNumber() + " ].");
			throw new IllegalArgumentsCustomException(
					"Ya se encuentra registrado un alojamiento con número de registro ["
							+ accomodationModel.getRegisterNumber() + " ].");
		}

		return accomodationRepo.save(accomodationModel);
	}

	/**
	 * Listado de todos los alojamientos registrados en la aplicación.
	 * 
	 * @return
	 */
	@Override
	public List<AccomodationModel> findAllAccomodations() {
		return accomodationRepo.findAll();
	}

	/**
	 * Obtención del alojamiento con número de registro <code>regNumber</code>.
	 */
	@Override
	public AccomodationModel getAccomodationById(final String regNumber) {

		if (!isStringNotBlank(regNumber)) {
			log.error("El número de registro está vacío.");
			throw new IllegalArgumentsCustomException("El número de registro está vacío.");
		}

		return accomodationRepo.findById(regNumber).get();
	}

	/**
	 * Borrado del alojamiento con número de registro <code>regNumber</code>.
	 * 
	 * @param regNumber
	 * 
	 * @return
	 */
	@Override
	public String removeAccomodationById(final String regNumber) {
		if (!isStringNotBlank(regNumber)) {
			log.error("El número de registro pasado como parámetro está vacío.");
			throw new IllegalArgumentsCustomException("El número de registro pasado como parámetro está vacío.");
		}

		// Comprobar si existe el alojamiento
		if (!accomodationRepo.existsById(regNumber)) {
			log.error("El alojamiento con número de registro [ " + regNumber + " ] no existe.");
			throw new NotFoundCustomException("El alojamiento con número de registro [ " + regNumber + " ] no existe.");
		}

		accomodationRepo.deleteById(regNumber);

		return "Alojamiento con número de registro [ " + regNumber + " ] eliminado correctamente";
	}

	/**
	 * Listado de los alojamientos de la ciudad <code>cityToSearch</code>.
	 * 
	 * @param cityToSearch
	 * 
	 * @return
	 */
	@Override
	public List<AccomodationModel> findByCity(final String cityToSearch) {
		if (!isStringNotBlank(cityToSearch)) {
			log.error("El valor [ " + cityToSearch + " ] está vacío o no es válido.");
			throw new IllegalArgumentsCustomException("El valor [ " + cityToSearch + " ] está vacío o no es válido.");
		}

		String listAccomodationsByCityQuery = "SELECT ac "
				+ "FROM AccomodationModel ac INNER JOIN ac.idAccomodationLocation al " + "WHERE al.city = :city";

		TypedQuery<AccomodationModel> accomodations = em.createQuery(listAccomodationsByCityQuery,
				AccomodationModel.class);

		accomodations.setParameter("city", cityToSearch);

		return accomodations != null ? accomodations.getResultList() : null;
	}

	/**
	 * Listado de alojamientos cercanos a unas coordenadas [ <code>lat</code> ,
	 * <code>lng</code> ]
	 * 
	 * Se especifica el radio de búsqueda en el parámetro <code>distance</code>.
	 * 
	 * Para realizar el cálculo se utiliza la fórmula de Haversine.
	 * 
	 * {@link #ACCOMODATION_LIMIT_RESULTS}
	 * 
	 * @param lat
	 * @param lng
	 * @param distance
	 * 
	 * @return
	 */
	@Override
	public List<AccomodationModel> findByNearby(final BigDecimal lat, final BigDecimal lng, final double distance) {

		if (!isValidGeographicCoordinate(lat, true)) {
			log.error("La latitud introducida no es válida.");
			throw new IllegalArgumentsCustomException("La latitud introducida no es válida.");
		}

		if (!isValidGeographicCoordinate(lng, false)) {
			log.error("La longitud introducida no es válida.");
			throw new IllegalArgumentsCustomException("La longitud introducida no es válida.");
		}

		if (!isDoubleValidAndPositive(distance)) {
			log.error("La distancia introducida no es válida.");
			throw new IllegalArgumentsCustomException("La distancia introducida no es válida.");
		}

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

	/**
	 * Actualización de los datos del alojamiento con número de registro
	 * <code>regNumber</code>.
	 * 
	 * @param regNumber
	 * @param accomodationToUpdate
	 * 
	 * @return
	 */
	@Override
	@Modifying
	public AccomodationModel updateAccomodationById(final String regNumber,
			final AccomodationModel accomodationToUpdate) {

		if (!isStringNotBlank(regNumber)) {
			log.error("El número de registro está vacío");
			throw new IllegalArgumentsCustomException("El número de registro está vacío");
		}

		// Detalles del alojamiento original
		AccomodationModel originalAccomodation = getAccomodationById(regNumber);

		if (!isNotNull(accomodationToUpdate)) {
			log.error("Alguno de los datos del alojamiento a actualizar no es válido");
			throw new IllegalArgumentsCustomException("Alguno de los datos del alojamiento a actualizar no es válido");
		}

		// Comprobar si existe el alojamiento a actualizar
		if (!accomodationRepo.existsById(regNumber)) {
			log.error("No existe un alojamiento con número de registro " + regNumber);
			throw new NotFoundCustomException("No existe un alojamiento con número de registro " + regNumber);
		}

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

		return accomodationRepo.save(originalAccomodation);
	}

	/**
	 * Listado de alojamientos filtrando por la categoría
	 * <code>accomodationCategory</code>.
	 * 
	 * @param accomodationCategory
	 * 
	 * @return
	 */
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

	/**
	 * Listado de alojamientos filtrando por un rango de precios comprendido entre
	 * <code>minPrice</code> y <code>maxPrice</code>.
	 * 
	 * @param minPrice
	 * @param maxPrice
	 * 
	 * @return
	 */
	@Override
	public List<AccomodationModel> findByPriceRange(final BigDecimal minPrice, final BigDecimal maxPrice) {
		if (!isBigDecimalValid(minPrice)) {
			log.error("El precio mínimo introducido no es válido.");
			throw new IllegalArgumentsCustomException("El precio mínimo introducido no es válido.");
		}

		if (!isBigDecimalValid(maxPrice)) {
			log.error("El precio máximo introducido no es válido.");
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
