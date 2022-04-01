package com.hosting.rest.api.services.Accomodation.AccomodationReview;

import static com.hosting.rest.api.Utils.AppUtils.isIntegerValidAndPositive;
import static com.hosting.rest.api.Utils.AppUtils.isNotNull;
import static com.hosting.rest.api.Utils.AppUtils.isStringNotBlank;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hosting.rest.api.exceptions.Accomodation.IllegalArguments.IllegalArgumentsCustomException;
import com.hosting.rest.api.models.Accomodation.AccomodationReview.AccomodationReviewModel;
import com.hosting.rest.api.repositories.Accomodation.AccomodationReview.IAccomodationReviewRepository;

@Service
public class AccomodationReviewServiceImpl implements IAccomodationReviewService {

	@PersistenceContext
	private EntityManager em;

	@Autowired
	private IAccomodationReviewRepository accomodationReviewRepo;

	@Override
	public AccomodationReviewModel addNewAccomodationReview(final AccomodationReviewModel accomodationToAdd) {

		if (!isNotNull(accomodationToAdd)) {
			throw new IllegalArgumentsCustomException(
					"Los datos introducidos para la creación de la valoración no son válidos.");
		}

		return accomodationReviewRepo.save(accomodationToAdd);
	}

	@Override
	public AccomodationReviewModel udpateAccomodationReview(final AccomodationReviewModel accomodationToUpdate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteAccomodationReviewById(final Integer accomodationReviewId) {
		if (!isIntegerValidAndPositive(accomodationReviewId)) {
			throw new IllegalArgumentsCustomException(
					"El número de registro [ " + accomodationReviewId + " ] no es válido.");
		}

		boolean existsAccomodationReview = accomodationReviewRepo.findById(accomodationReviewId).get() != null;

		if (existsAccomodationReview) {
			accomodationReviewRepo.deleteById(accomodationReviewId);
		}
	}

	@Override
	public List<AccomodationReviewModel> findAllAccomodationReviews(final String regNumber) {
		if (!isStringNotBlank(regNumber)) {
			throw new IllegalArgumentsCustomException("El número de registro [ " + regNumber + " ] no es válido.");
		}

		String findByAccomodationIdQuery = "SELECT ar "
				+ "FROM AccomodationReviewModel ar INNER JOIN ar.idAccomodation ac "
				+ "WHERE ac.registerNumber = :regNumber";

		TypedQuery<AccomodationReviewModel> accomodationReviews = em.createQuery(findByAccomodationIdQuery,
				AccomodationReviewModel.class);

		accomodationReviews.setParameter("regNumber", regNumber);

		return accomodationReviews.getResultList();
	}

	@Override
	public AccomodationReviewModel findAccomodationById(final Integer accomodationReviewId) {
		if (!isIntegerValidAndPositive(accomodationReviewId)) {
			throw new IllegalArgumentsCustomException(
					"El número de registro [ " + accomodationReviewId + " ] no es válido.");
		}

		return accomodationReviewRepo.findById(accomodationReviewId).get();
	}

	@Override
	public List<AccomodationReviewModel> findByUserId(final Integer userId) throws NumberFormatException {

		if (!isIntegerValidAndPositive(userId)) {
			throw new IllegalArgumentsCustomException("El id de usuario [ " + userId + " ] no es válido.");
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
		if (!isStringNotBlank(regNumber)) {
			throw new IllegalArgumentsCustomException("El número de registro [ " + regNumber + " ] no es válido.");
		}

		String getAccomodationReviewAverageStars = "SELECT AVG(arm.stars) "
				+ "FROM AccomodationReviewModel arm INNER JOIN arm.idAccomodation ac "
				+ "WHERE ac.registerNumber = :regNumber";

		TypedQuery<Double> starsAverage = em.createQuery(getAccomodationReviewAverageStars, Double.class);

		starsAverage.setParameter("regNumber", regNumber);

		return Double.valueOf(starsAverage.getSingleResult());
	}

	@Override
	public List<AccomodationReviewModel> findLatestAccomodationReviews(final String regNumber) {
		if (!isStringNotBlank(regNumber)) {
			throw new IllegalArgumentsCustomException("El número de registro [ " + regNumber + " ] no es válido.");
		}

		String findLasAccomodationReviewsQuery = "SELECT arm "
				+ "FROM AccomodationReviewModel arm INNER JOIN arm.idAccomodation ac " + "WHERE ac.id = :regNumber "
				+ "ORDER BY arm.createdAt DESC";

		TypedQuery<AccomodationReviewModel> latestReviews = em.createQuery(findLasAccomodationReviewsQuery,
				AccomodationReviewModel.class);

		latestReviews.setParameter("regNumber", regNumber);

		return latestReviews.setMaxResults(LATEST_ACCOMODATION_REVIEWS_LIMIT).getResultList();
	}

	@Override
	public Integer countAccomodationReviewFromAccomodation(final String regNumber) {
		if (!isStringNotBlank(regNumber)) {
			throw new IllegalArgumentsCustomException("El número de registro " + regNumber + " no es válido.");
		}

		return null;
	}

}
