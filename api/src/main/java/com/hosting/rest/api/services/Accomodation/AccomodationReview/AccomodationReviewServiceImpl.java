package com.hosting.rest.api.services.Accomodation.AccomodationReview;

import static com.hosting.rest.api.Utils.AppUtils.isIntegerValidAndPositive;
import static com.hosting.rest.api.Utils.AppUtils.isNotNull;
import static com.hosting.rest.api.Utils.AppUtils.isStringNotBlank;
import static com.hosting.rest.api.Utils.ServiceParamValidator.validateParam;
import static com.hosting.rest.api.Utils.ServiceParamValidator.validateParamNotFound;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hosting.rest.api.models.Accomodation.AccomodationReview.AccomodationReviewModel;
import com.hosting.rest.api.repositories.Accomodation.IAccomodationRepository;
import com.hosting.rest.api.repositories.Accomodation.AccomodationReview.IAccomodationReviewRepository;
import com.hosting.rest.api.repositories.User.IUserRepository;

/**
 * 
 * @author Francisco Coya · https://github.com/FranciscoCoya
 * @version v1.0.2
 * @description Implementa las acciones relacionadas con las valoraciones de los
 *              alojamientos.
 * 
 **/
@Service
public class AccomodationReviewServiceImpl implements IAccomodationReviewService {

	@PersistenceContext
	private EntityManager em;

	@Autowired
	private IAccomodationReviewRepository accomodationReviewRepo;

	@Autowired
	private IAccomodationRepository accomodationRepo;

	@Autowired
	private IUserRepository userRepo;

	/**
	 * Añade una nueva valoración a un alojamiento.
	 * 
	 * @param accomodationReviewToAdd
	 * 
	 * @return
	 */
	@Override
	public AccomodationReviewModel addNewAccomodationReview(final AccomodationReviewModel accomodationReviewToAdd) {

		// Validar valoracion alojamiento pasada como parámetro
		validateParam(isNotNull(accomodationReviewToAdd),
				"Los datos introducidos para la creación de la valoración no son válidos.");

		validateParamNotFound(!accomodationReviewRepo.existsById(accomodationReviewToAdd.getId()),
				"Ya existe una valoración con esos datos.");

		return accomodationReviewRepo.save(accomodationReviewToAdd);
	}

	/**
	 * Actualización de los datos de la valoración de alojamiento con id
	 * <code>accomodationReviewId</code>.
	 * 
	 * @param accomodationReviewId
	 * @param accomodationToUpdate
	 * 
	 * @return
	 * 
	 * @throws NumberFormatException Si el id de la valoración del alojamiento no es
	 *                               un número.
	 */
	@Override
	public AccomodationReviewModel udpateAccomodationReview(final Integer accomodationReviewId,
			final AccomodationReviewModel accomodationToUpdate) throws NumberFormatException {

		// Validar id de valoracion
		validateParam(isIntegerValidAndPositive(accomodationReviewId),
				"El id de la valoración " + accomodationReviewId + " no es válido.");

		// Validar valoracion alojamiento pasada como parametro
		validateParam(isNotNull(accomodationToUpdate),
				"Alguno de los datos introducidos para la valoración del alojamiento no es válido.");

		// Comprobar si existe la valoracion
		validateParamNotFound(accomodationReviewRepo.existsById(accomodationReviewId),
				"No existe una valoración de alojamiento con id " + accomodationReviewId);

		// Actualizar contenido y estrellas de la valoración
		AccomodationReviewModel originalAccomodationReview = accomodationReviewRepo.getById(accomodationReviewId);

		originalAccomodationReview.setContent(accomodationToUpdate.getContent());
		originalAccomodationReview.setStars(accomodationToUpdate.getStars());

		return accomodationReviewRepo.save(originalAccomodationReview);
	}

	/**
	 * Borrado de una valoración de alojamiento con el id
	 * <code>accomodationReviewId</code>.
	 * 
	 * @param accomodationReviewId
	 * 
	 * @throws NumberFormatException Si el id de la valoración del alojamiento no es
	 *                               un número.
	 */
	@Override
	public void deleteAccomodationReviewById(final Integer accomodationReviewId) throws NumberFormatException {
		// Validar id de la valoración del alojamiento
		validateParam(isIntegerValidAndPositive(accomodationReviewId),
				"El número de registro [ " + accomodationReviewId + " ] no es válido.");

		// Comprobar si existe la valoracion del alojamiento
		validateParamNotFound(accomodationReviewRepo.existsById(accomodationReviewId),
				"No existe la valoración de alojamiento con id " + accomodationReviewId);

		accomodationReviewRepo.deleteById(accomodationReviewId);
	}

	/**
	 * Listado de todas las valoraciones del alojamiento con número de registro
	 * <code>regNumber</code>.
	 * 
	 * @param regNumber
	 * 
	 * @return
	 */
	@Override
	public List<AccomodationReviewModel> findAllAccomodationReviews(final String regNumber) {

		// Validar número de registro del alojamiento
		validateParam(isStringNotBlank(regNumber), "El número de registro [ " + regNumber + " ] no es válido.");

		// Comprobar si existe el alojamiento
		validateParamNotFound(accomodationRepo.existsById(regNumber),
				"No existe el alojamiento con número de registro " + regNumber);

		String findByAccomodationIdQuery = "SELECT ar "
				+ "FROM AccomodationReviewModel ar INNER JOIN ar.idAccomodation ac "
				+ "WHERE ac.registerNumber = :regNumber";

		TypedQuery<AccomodationReviewModel> accomodationReviews = em.createQuery(findByAccomodationIdQuery,
				AccomodationReviewModel.class);

		accomodationReviews.setParameter("regNumber", regNumber);

		return accomodationReviews.getResultList();
	}

	/**
	 * Obtención de la valoración del alojamiento con id
	 * <code>accomodationReviewId</code>.
	 * 
	 * @param accomodationReviewId
	 * 
	 * @return
	 * 
	 * @throw NumberFormatException Si el id de la valoracion del alojamiento no es
	 *        un número.
	 */
	@Override
	public AccomodationReviewModel findAccomodationById(final Integer accomodationReviewId)
			throws NumberFormatException {

		// Validar id de la valoracion
		validateParam(isIntegerValidAndPositive(accomodationReviewId),
				"El número de registro [ " + accomodationReviewId + " ] no es válido.");

		return accomodationReviewRepo.findById(accomodationReviewId).get();
	}

	/**
	 * Listado de todas las valoraciones realizadas por el usuario con id
	 * <code>userId</code>.
	 * 
	 * @param userId
	 * 
	 * @return
	 * 
	 * @throws NumberFormatException Si el id del usuario no es un número.
	 */
	@Override
	public List<AccomodationReviewModel> findByUserId(final Integer userId) throws NumberFormatException {

		// Validar id del usuario
		validateParam(isIntegerValidAndPositive(userId), "El id de usuario [ " + userId + " ] no es válido.");

		// Comprobar si existe el usuario
		validateParamNotFound(userRepo.existsById(userId), "No existe un usuario con id " + userId);

		String findAccomodationByUserIdQuery = "SELECT arm "
				+ "FROM AccomodationReviewModel arm INNER JOIN arm.idUser u " + "WHERE u.id = :userId";

		TypedQuery<AccomodationReviewModel> accomodationReviews = em.createQuery(findAccomodationByUserIdQuery,
				AccomodationReviewModel.class);

		accomodationReviews.setParameter("userId", userId);

		return accomodationReviews.getResultList();
	}

	/**
	 * Número medio de estrellas de las valoraciones recibidas en el alojamiento con
	 * número de registro <code>regNumber</code>.
	 * 
	 * @param regNumber
	 * 
	 * @return
	 */
	@Override
	public Double getAccomodationReviewAverageStars(final String regNumber) {
		// Validar número de registro de alojamiento.
		validateParam(isStringNotBlank(regNumber), "El número de registro [ " + regNumber + " ] no es válido.");

		// Comprobar si existe el alojamiento
		validateParamNotFound(accomodationRepo.existsById(regNumber),
				"No existe el alojamiento con número de registro " + regNumber);

		String getAccomodationReviewAverageStars = "SELECT AVG(arm.stars) "
				+ "FROM AccomodationReviewModel arm INNER JOIN arm.idAccomodation ac "
				+ "WHERE ac.registerNumber = :regNumber";

		TypedQuery<Double> starsAverage = em.createQuery(getAccomodationReviewAverageStars, Double.class);

		starsAverage.setParameter("regNumber", regNumber);

		return Double.valueOf(starsAverage.getSingleResult());
	}

	/**
	 * Listado de las valoraciones más recientes publicadas en el alojamiento con
	 * número de registro <code>regNumber</code>.
	 * 
	 * {@link #LATEST_ACCOMODATION_REVIEWS_LIMIT }
	 * 
	 * @param regNumber
	 * 
	 * @return
	 */
	@Override
	public List<AccomodationReviewModel> findLatestAccomodationReviews(final String regNumber) {

		// Validar número de registro de alojamiento
		validateParam(isStringNotBlank(regNumber), "El número de registro [ " + regNumber + " ] no es válido.");

		// Comprobar si existe el alojamiento
		validateParamNotFound(accomodationRepo.existsById(regNumber),
				"No existe el alojamiento con número de registro " + regNumber);

		String findLasAccomodationReviewsQuery = "SELECT arm "
				+ "FROM AccomodationReviewModel arm INNER JOIN arm.idAccomodation ac " + "WHERE ac.id = :regNumber "
				+ "ORDER BY arm.createdAt DESC";

		TypedQuery<AccomodationReviewModel> latestReviews = em.createQuery(findLasAccomodationReviewsQuery,
				AccomodationReviewModel.class);

		latestReviews.setParameter("regNumber", regNumber);

		return latestReviews.setMaxResults(LATEST_ACCOMODATION_REVIEWS_LIMIT).getResultList();
	}
}
