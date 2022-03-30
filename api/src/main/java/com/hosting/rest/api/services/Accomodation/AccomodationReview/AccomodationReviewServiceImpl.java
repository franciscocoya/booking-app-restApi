package com.hosting.rest.api.services.Accomodation.AccomodationReview;

import static com.hosting.rest.api.Utils.AppUtils.isIntegerValidAndPositive;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hosting.rest.api.exceptions.Accomodation.AccomodationReview.IllegalArgument.IllegalAccomodationReviewArgumentsException;
import com.hosting.rest.api.models.Accomodation.AccomodationReviewModel;
import com.hosting.rest.api.repositories.Accomodation.AccomodationReview.IAccomodationReviewRepository;

@Service
public class AccomodationReviewServiceImpl implements IAccomodationReviewService {

	@PersistenceContext
	private EntityManager em;

	@Autowired
	private IAccomodationReviewRepository accomodationReviewRepo;

	@Override
	public AccomodationReviewModel addNewAccomodationReview(final AccomodationReviewModel accomodationToAdd) {
		return accomodationReviewRepo.save(accomodationToAdd);
	}

	@Override
	public AccomodationReviewModel udpateAccomodationReview(final AccomodationReviewModel accomodationToUpdate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteAccomodationReviewById(final Integer accomodationReviewId) {
		accomodationReviewRepo.deleteById(accomodationReviewId);
	}

	@Override
	public List<AccomodationReviewModel> findAllAccomodationReviews(final String regNumber) {
		return accomodationReviewRepo.findByAccomodationId(regNumber);
	}

	@Override
	public AccomodationReviewModel findAccomodationById(final Integer accomodationReviewId) {
		return accomodationReviewRepo.findById(accomodationReviewId).get();
	}

	@Override
	public List<AccomodationReviewModel> findByUserId(final Integer userId) {

		if (!isIntegerValidAndPositive(userId)) {
			throw new IllegalAccomodationReviewArgumentsException("El id de usuario [ " + userId + " ] no es válido.");
		}

		String findAccomodationByUserIdQuery = "SELECT arm "
				+ "FROM AccomodationReviewModel arm INNER JOIN arm.idUser u " + "WHERE u.id = :userId";

		TypedQuery<AccomodationReviewModel> accomodationReviews = em.createQuery(findAccomodationByUserIdQuery,
				AccomodationReviewModel.class);

		accomodationReviews.setParameter("userId", userId);

		return accomodationReviews.getResultList();
	}

	@Override
	public Double getAccomodationReviewAverageStars(final String regNumber) {
		String getAccomodationReviewAverageStars = "SELECT AVG(arm.stars) "
				+ "FROM AccomodationReviewModel arm INNER JOIN arm.idAccomodation ac "
				+ "WHERE ac.registerNumber = :regNumber";

		TypedQuery<Double> starsAverage = em.createQuery(getAccomodationReviewAverageStars, Double.class);

		starsAverage.setParameter("regNumber", regNumber);

		return Double.valueOf(starsAverage.getSingleResult());
	}

	@Override
	public List<AccomodationReviewModel> findLatestAccomodationReviews(final String regNumber) {
		String findLasAccomodationReviewsQuery = "SELECT arm "
				+ "FROM AccomodationReviewModel arm INNER JOIN arm.idAccomodation ac " + "WHERE ac.id = :regNumber "
				+ "ORDER BY arm.createdAt DESC";

		TypedQuery<AccomodationReviewModel> latestReviews = em.createQuery(findLasAccomodationReviewsQuery,
				AccomodationReviewModel.class);

		latestReviews.setParameter("regNumber", regNumber);

		return latestReviews.setMaxResults(LATEST_ACCOMODATION_REVIEWS_LIMIT).getResultList();
	}

}
