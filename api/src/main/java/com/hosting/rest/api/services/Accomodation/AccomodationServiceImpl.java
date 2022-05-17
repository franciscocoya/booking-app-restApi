package com.hosting.rest.api.services.Accomodation;

import static com.hosting.rest.api.Utils.AppUtils.isBigDecimalValid;
import static com.hosting.rest.api.Utils.AppUtils.isDoubleValidAndPositive;
import static com.hosting.rest.api.Utils.AppUtils.isIntegerValidAndPositive;
import static com.hosting.rest.api.Utils.AppUtils.isNotNull;
import static com.hosting.rest.api.Utils.AppUtils.isStringNotBlank;
import static com.hosting.rest.api.Utils.AppUtils.isValidGeographicCoordinate;
import static com.hosting.rest.api.Utils.ServiceGlobalValidations.checkPageNumber;
import static com.hosting.rest.api.Utils.ServiceGlobalValidations.checkPageSize;
import static com.hosting.rest.api.Utils.ServiceParamValidator.validateParam;
import static com.hosting.rest.api.Utils.ServiceParamValidator.validateParamNotFound;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import com.hosting.rest.api.models.Accomodation.AccomodationModel;
import com.hosting.rest.api.models.Accomodation.AccomodationImage.AccomodationImageModel;
import com.hosting.rest.api.repositories.Accomodation.IAccomodationRepository;
import com.hosting.rest.api.repositories.User.UserHost.IUserHostRepository;

/**
 * 
 * @author Francisco Coya
 * @version v1.0.3
 * @description Servicio que implementa las acciones relacionadas a los
 *              alojamientos.
 * 
 **/
@Service
public class AccomodationServiceImpl implements IAccomodationService {

	@PersistenceContext
	private EntityManager em;

	@Autowired
	private IAccomodationRepository accomodationRepo;

	@Autowired
	private IUserHostRepository userRepo;

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
		validateParam(
				isNotNull(accomodationModel) || isNotNull(accomodationModel.getRegisterNumber())
						|| isNotNull(accomodationModel.getIdUserHost()),
				"Los datos introducidos para el alojamiento no son válidos o falta algún dato.");

		// Comprobar si existe el alojamiento
		validateParam(!accomodationRepo.existsById(accomodationModel.getRegisterNumber()),
				"Ya se encuentra registrado un alojamiento con número de registro ["
						+ accomodationModel.getRegisterNumber() + " ].");

		return accomodationRepo.save(accomodationModel);
	}

	/**
	 * Listado de todos los alojamientos registrados en la aplicación.
	 * 
	 * @param pageNumber
	 * @param size
	 * 
	 * @return
	 */
	@Override
	public Page<AccomodationModel> findAllAccomodations(final Integer pageNumber, final Integer pageSize) {
		// Comprobar que el número de página y el tamaño de esta son válidos.
		checkPageNumber(pageNumber);
		checkPageSize(pageSize);

		return accomodationRepo.findAll(PageRequest.of(pageNumber, pageSize, Sort.by("createdAt").descending()));
	}

	/**
	 * Obtención del alojamiento con número de registro <code>regNumber</code>.
	 * 
	 * @see #validateParam
	 * 
	 * @param regNumber
	 * 
	 * @return
	 */
	@Override
	public AccomodationModel getAccomodationById(final String regNumber) {
		// Validar número de registro del alojamiento.
		validateParam(isStringNotBlank(regNumber), "El número de registro está vacío.");

		return accomodationRepo.findById(regNumber).orElse(null);
	}

	/**
	 * Borrado del alojamiento con número de registro <code>regNumber</code>.
	 * 
	 * @see #validateParam
	 * 
	 * @param regNumber
	 * 
	 * @return
	 */
	@Override
	public String removeAccomodationById(final String regNumber) {
		// Validar número de registro del alojamiento.
		validateParam(isStringNotBlank(regNumber), "El número de registro pasado como parámetro está vacío.");

		// Comprobar que existe el alojamiento.
		validateParamNotFound(!accomodationRepo.existsById(regNumber),
				"El alojamiento con número de registro [ " + regNumber + " ] no existe.");

		accomodationRepo.deleteById(regNumber);

		return "Alojamiento con número de registro [ " + regNumber + " ] eliminado correctamente";
	}

	/**
	 * Listado de los alojamientos de la ciudad <code>cityToSearch</code>.
	 * 
	 * @param cityToSearch
	 * @param pageNumber
	 * @param size
	 * 
	 * @return
	 */
	@Override
	public Page<AccomodationModel> findByCity(final String cityToSearch, final Integer pageNumber, final Integer size) {
		// Validar ciudad
		validateParam(isStringNotBlank(cityToSearch), "El valor [ " + cityToSearch + " ] está vacío o no es válido.");

		// Comprobar que el número de página y el tamaño de esta son válidos.
		checkPageNumber(pageNumber);
		checkPageSize(size);

		String listAccomodationsByCityQuery = "SELECT ac "
				+ "FROM AccomodationModel ac INNER JOIN ac.idAccomodationLocation al " + "WHERE LOWER(al.city) "
						+ "LIKE LOWER(:city)";

		TypedQuery<AccomodationModel> accomodations = em.createQuery(listAccomodationsByCityQuery,
				AccomodationModel.class);

		accomodations.setParameter("city", cityToSearch);

		// Número de alojamientos a mostrar
		accomodations.setMaxResults(size);

		return new PageImpl<AccomodationModel>(accomodations.getResultList());
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
		// Validar latitud
		validateParam(isValidGeographicCoordinate(lat, true), "La latitud introducida no es válida.");

		// Validar latitud
		validateParam(isValidGeographicCoordinate(lng, false), "La longitud introducida no es válida.");

		// Validar radio búsqueda
		validateParam(isDoubleValidAndPositive(distance), "La distancia introducida no es válida.");

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
		// Validar número registro alojamiento.
		validateParam(isStringNotBlank(regNumber), "El número de registro está vacío");

		// Detalles del alojamiento original
		AccomodationModel originalAccomodation = getAccomodationById(regNumber);

		// Validar modelo Alojamiento pasado como parámetro.
		validateParam(isNotNull(accomodationToUpdate), "Alguno de los datos del alojamiento a actualizar no es válido");

		// Comprobar si existe el alojamiento a actualizar
		validateParamNotFound(accomodationRepo.existsById(regNumber),
				"No existe un alojamiento con número de registro " + regNumber);

		// Descripción
		originalAccomodation.setDescription(accomodationToUpdate.getDescription());

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
		// Validar categoria
		validateParam(isStringNotBlank(accomodationCategory), "La categoría introducida está vacía o no es válida.");

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
		// Validar precio mínimo.
		validateParam(isBigDecimalValid(minPrice), "El precio mínimo introducido no es válido.");

		// Validar precio máximo.
		validateParam(isBigDecimalValid(maxPrice), "El precio máximo introducido no es válido.");

		String findByAccomodationCategoryQuery = "SELECT am " + "FROM AccomodationModel am "
				+ "WHERE am.pricePerNight BETWEEN :minPrice and :maxPrice " + "ORDER BY am.pricePerNight DESC";

		TypedQuery<AccomodationModel> accomodations = em.createQuery(findByAccomodationCategoryQuery,
				AccomodationModel.class);

		accomodations.setParameter("minPrice", minPrice);
		accomodations.setParameter("maxPrice", maxPrice);

		return accomodations.getResultList();
	}

	/**
	 * Listado de alojamientos delimitado a <code>maxNumberOfAccomodations</code>
	 * resultados.
	 * 
	 * @see #validateParam
	 * 
	 * @param maxNumberOfAccomodations
	 * 
	 * @return
	 */
	@Override
	public List<AccomodationModel> findNAccomodations(final Integer maxNumberOfAccomodations) {
		// Validar número máximo de alojamientos a mostrar.
		validateParam(isIntegerValidAndPositive(maxNumberOfAccomodations),
				"El número máximo de resultados a mostrar no es válido.");

		TypedQuery<AccomodationModel> accomodations = em.createQuery("SELECT am FROM AccomodationModel am",
				AccomodationModel.class);

		accomodations.setMaxResults(maxNumberOfAccomodations);

		return accomodations.getResultList();
	}

	/**
	 * Listado de todos los alojamientos del usuario <code>userId</code>.
	 * 
	 * @param userId
	 * 
	 * @return
	 */
	@Override
	public List<AccomodationModel> findByUserId(final Integer userId) {
		// Validar id de usuario
		validateParam(isIntegerValidAndPositive(userId), "El id de usuario [ " + userId + " ] no es válido.");

		// Comprobar si el usuario existe
		validateParam(userRepo.existsById(userId), "No existe ningún usuario host con el id " + userId);

		TypedQuery<AccomodationModel> accomodationsByUserId = em.createQuery(
				"SELECT am FROM AccomodationModel am WHERE am.idUserHost.id = :userId", AccomodationModel.class);

		accomodationsByUserId.setParameter("userId", userId);

		return accomodationsByUserId.getResultList();
	}

	@Transactional
	@Override
	public void removeAccomodationImage(final String regNumber, final Integer imageId) {
		// Validar número de registro
		validateParam(isStringNotBlank(regNumber), "El número de registro introducido está vacío");

		// Comprobar que existe el alojamiento
		validateParamNotFound(accomodationRepo.existsById(regNumber),
				"El alojamiento con número de registro [ " + regNumber + " ] no existe.");

		// Validar id de la imagen
		validateParam(isIntegerValidAndPositive(imageId), "El id de la imagen a borrar no es válido.");

		em.createQuery(
				"DELETE FROM AccomodationAccImageModel accim WHERE accim.accomodationAccImageId.idAccomodationImage.id = :imgId")
				.setParameter("imgId", imageId).executeUpdate();

		em.createQuery("DELETE FROM AccomodationImageModel aim WHERE aim.id = :imgId").setParameter("imgId", imageId)
				.executeUpdate();
	}

	@Transactional
	@Override
	public AccomodationModel addNewImageToExistingAccomodation(final String regNumber,
			final AccomodationImageModel imageToAdd) {

		// Validar número de registro
		validateParam(isStringNotBlank(regNumber), "El número de registro introducido está vacío");

		// Comprobar que existe el alojamiento
		validateParamNotFound(accomodationRepo.existsById(regNumber),
				"El alojamiento con número de registro [ " + regNumber + " ] no existe.");

		// Validar la imagen
		validateParam(isNotNull(imageToAdd), "La imagen a añadir está vacía.");

		// Crear la imagen
		em.createNativeQuery("INSERT INTO ACCOMODATION_IMAGE(IMG_URL) VALUES(:imgUrl)")
				.setParameter("imgUrl", imageToAdd.getImageUrl()).executeUpdate();

		// Obtener el id de la última imagen
		TypedQuery<AccomodationImageModel> lastImage = em.createQuery(
				"SELECT aim FROM AccomodationImageModel aim ORDER BY aim.id DESC", AccomodationImageModel.class);

		Integer lastImageId = lastImage.setMaxResults(1).getSingleResult().getId();

		// Añadir la imagen al alojamiento indicado
		em.createNativeQuery("INSERT INTO ACCOMODATION_ACC_IMAGE(ID_ACC, ID_ACC_IMAGE) VALUES(:regNumber, :imgId)")
				.setParameter("regNumber", regNumber).setParameter("imgId", lastImageId).executeUpdate();

		return accomodationRepo.findById(regNumber).get();
	}

	/**
	 * Listado de todas las ciudades donde se han publicado alojamientos en la app.
	 */
	@Override
	public List<String> findAllAccomodationCities() {		
		String getAccomodationCitiesQuery = "SELECT DISTINCT al.city "
				+ "FROM AccomodationModel am INNER JOIN am.idAccomodationLocation al";
		
		TypedQuery<String> citiesToReturn = em.createQuery(getAccomodationCitiesQuery, String.class);
		
		return citiesToReturn.getResultList();
	}

}
