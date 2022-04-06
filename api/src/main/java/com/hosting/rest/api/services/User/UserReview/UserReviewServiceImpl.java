package com.hosting.rest.api.services.User.UserReview;

import static com.hosting.rest.api.Utils.AppUtils.isIntegerValidAndPositive;
import static com.hosting.rest.api.Utils.AppUtils.isNotNull;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hosting.rest.api.exceptions.IllegalArguments.IllegalArgumentsCustomException;
import com.hosting.rest.api.exceptions.NotFound.NotFoundCustomException;
import com.hosting.rest.api.models.User.HostReviewModel;
import com.hosting.rest.api.repositories.User.IUserRepository;
import com.hosting.rest.api.repositories.User.UserReview.IUserReviewRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author Francisco Coya
 * @version v1.0.0
 * @apiNote Servicio que gestiona las valoraciones de un usuario de la
 *          aplicación.
 *
 */
@Slf4j
@Service
public class UserReviewServiceImpl implements IUserReviewService {

	@PersistenceContext
	private EntityManager em;

	@Autowired
	private IUserReviewRepository userReviewRepo;

	@Autowired
	private IUserRepository userRepo;

	/**
	 * Añade una nueva valoración a un usuario.
	 * 
	 * @param newUserReview
	 * 
	 * @return
	 */
	@Override
	public HostReviewModel addNewUserReview(final HostReviewModel newUserReview) {
		if (!isNotNull(newUserReview)) {
			log.error("Los datos introducidos para el nuevo usuario no son válidos.");
			throw new IllegalArgumentsCustomException("Los datos introducidos para el nuevo usuario no son válidos.");
		}

		boolean existsUserReview = userReviewRepo.findById(newUserReview.getId()).get() != null;

		if (existsUserReview) {
			log.error("Ya existe una valoración para el usuario.");
			throw new IllegalArgumentsCustomException("Ya existe una valoración para el usuario.");
		}

		return userReviewRepo.save(newUserReview);
	}

	/**
	 * Actualiza el contenido de una valoración de un usuario.
	 * 
	 * @param userId
	 * @param userReviewToUpdate
	 * 
	 * @return
	 */
	@Transactional
	@Override
	public HostReviewModel updateUserReview(final Integer userId, final HostReviewModel userReviewToUpdate) {

		if (!isIntegerValidAndPositive(userId)) {
			log.error("El id del usuario [ " + userId + " ] introducido no es válido.");
			throw new IllegalArgumentsCustomException("El id del usuario [ " + userId + " ] introducido no es válido.");
		}

		HostReviewModel originalHostReview = userReviewRepo.findById(userId).get();

		if (!isNotNull(originalHostReview)) {
			log.error("La valoración a actualizar los datos no existe.");
			throw new NotFoundCustomException("La valoración a actualizar los datos no existe.");
		}

		if (!userRepo.existsById(userId)) {
			throw new NotFoundCustomException("El usuario con id [ " + userId + " ] no existe.");
		}

		// Contenido de la review
		originalHostReview.setContent(userReviewToUpdate.getContent());

		// Estrellas de la review
		originalHostReview.setStars(userReviewToUpdate.getStars());

		return userReviewRepo.save(originalHostReview);
	}

	/**
	 * Elimina una valoración de un usuario por el id de usuario <code>userId</code>
	 * 
	 * @param userId
	 * 
	 */
	@Override
	public void deleteUserReview(final Integer userId) {
		// TODO: Comprobar id valido
		if (!isIntegerValidAndPositive(userId)) {
			log.error("El id de usuario [ " + userId + " ] no es válido.");
			throw new IllegalArgumentsCustomException("El id de usuario [ " + userId + " ] no es válido.");
		}

		if (!userRepo.existsById(userId)) {
			throw new NotFoundCustomException("El usuario con id [ " + userId + " ] a borrar su valoración no existe.");
		}

		userReviewRepo.deleteById(userId);
	}

	/**
	 * Listado de las valoraciones a un usuario <code>userId</code>.
	 * 
	 * @param userId
	 * 
	 * @return
	 */
	@Override
	public List<HostReviewModel> findByUserId(final Integer userId) {

		if (!isIntegerValidAndPositive(userId)) {
			log.error("El id de usuario [ " + userId + " ] no es válido.");
			throw new IllegalArgumentsCustomException("El id de usuario [ " + userId + " ] no es válido.");
		}

		String findByUserIdQuery = "SELECT hr " + " FROM HostReviewModel hr " + " INNER JOIN hr.idUserA hu "
				+ " WHERE hu.id = :userId";

		TypedQuery<HostReviewModel> userReviews = em.createQuery(findByUserIdQuery, HostReviewModel.class);

		userReviews.setParameter("userId", userId);

		return userReviews.getResultList();
	}

}
