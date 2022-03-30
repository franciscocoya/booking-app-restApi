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

import com.hosting.rest.api.exceptions.Accomodation.IllegalArguments.IllegalAccomodationArgumentsException;
import com.hosting.rest.api.exceptions.Accomodation.NotFound.AccomodationNotFoundException;
import com.hosting.rest.api.exceptions.User.UserReview.IllegalArgument.IllegalUserReviewArgumentException;
import com.hosting.rest.api.models.User.HostReviewModel;
import com.hosting.rest.api.repositories.User.UserReview.IUserReviewRepository;

@Service
public class UserReviewServiceImpl implements IUserReviewService {

	@PersistenceContext
	private EntityManager em;

	@Autowired
	private IUserReviewRepository userReviewRepo;

	@Override
	public HostReviewModel addNewUserReview(final HostReviewModel newUserReview) {
		if (!isNotNull(newUserReview)) {
			throw new IllegalUserReviewArgumentException(
					"Los datos introducidos para el nuevo usuario no son válidos.");
		}

		boolean existsUserReview = userReviewRepo.findById(newUserReview.getId()).get() != null;

		if (existsUserReview) {
			throw new IllegalUserReviewArgumentException("Ya existe una valoración para el usuario.");
		}

		return userReviewRepo.save(newUserReview);
	}

	@Override
	@Transactional
	public HostReviewModel updateUserReview(final Integer userId, final HostReviewModel userReviewToUpdate) {

		if (!isIntegerValidAndPositive(userId)) {
			throw new IllegalAccomodationArgumentsException("El id del usuario introducido no es válido.");
		}

		HostReviewModel originalHostReview = userReviewRepo.findById(userId).get();

		if (!isNotNull(originalHostReview)) {
			throw new AccomodationNotFoundException("La valoración de un usuario a actualizar no existe.");
		}

		// Contenido de la review
		originalHostReview.setContent(userReviewToUpdate.getContent());

		// Estrellas de la review
		originalHostReview.setStars(userReviewToUpdate.getStars());

		return userReviewRepo.save(originalHostReview);
	}

	@Override
	public void deleteUserReview(final Integer userId) {
		userReviewRepo.deleteById(userId);
	}

	@Override
	public List<HostReviewModel> findByUserId(final Integer userId) {

		// TODO: Revisar
		if (!isIntegerValidAndPositive(userId)) {
			throw new IllegalUserReviewArgumentException("El id de usuario [ " + userId + " ] no es válido.");
		}

		/**
		 * Listado de las valoraciones a un usuario <code>userId</code>.
		 */
		String findByUserIdQuery = "SELECT hr " + " FROM HostReviewModel hr " + " INNER JOIN hr.idUserA hu "
				+ " WHERE hu.id = :userId";

		TypedQuery<HostReviewModel> userReviews = em.createQuery(findByUserIdQuery, HostReviewModel.class);

		userReviews.setParameter("userId", userId);

		return userReviews.getResultList();
	}

}
