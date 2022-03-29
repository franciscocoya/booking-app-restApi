package com.hosting.rest.api.services.User.UserReview;

import static com.hosting.rest.api.Utils.AppUtils.isIntegerValid;
import static com.hosting.rest.api.Utils.AppUtils.isStringNotBlank;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
		return userReviewRepo.save(newUserReview);
	}

	@Override
	@Transactional
	public HostReviewModel updateUserReview(final Integer userId, final HostReviewModel userReviewToUpdate) {

		HostReviewModel originalHostReview = isIntegerValid(userId) ? userReviewRepo.findById(userId).get() : null;

		String contentToUpdate = isStringNotBlank(userReviewToUpdate.getContent()) ? userReviewToUpdate.getContent()
				: originalHostReview.getContent();

		Integer starsToUpdate = isIntegerValid(userReviewToUpdate.getStars()) ? userReviewToUpdate.getStars()
				: originalHostReview.getStars();

		// Contenido de la review
		originalHostReview.setContent(contentToUpdate);

		// Estrellas de la review
		originalHostReview.setStars(starsToUpdate);

		return originalHostReview != null ? userReviewRepo.save(originalHostReview) : null;
	}

	@Override
	public void deleteUserReview(final Integer userId) {
		userReviewRepo.deleteById(userId);
	}

	@Override
	public List<HostReviewModel> findByUserId(final Integer userId) {
		
		// TODO: Revisar
		if(!isIntegerValid(userId)) {
			return null;
		}
		
		/**
		 * Listado de las valoraciones a un usuario <code>userId</code>.
		 */
		String findByUserIdQuery = "select hr" + " from HostReviewModel hr inner join hr.idUserA hu" + " where hu.id = :userId";

		TypedQuery<HostReviewModel> userReviews = em.createQuery(findByUserIdQuery, HostReviewModel.class);

		userReviews.setParameter("userId", userId);

		return userReviews.getResultList();
	}

}
