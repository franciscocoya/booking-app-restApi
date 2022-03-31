package com.hosting.rest.api.services.Accomodation.AccomodationReview;

import java.util.List;

import com.hosting.rest.api.models.Accomodation.AccomodationReviewModel;

public interface IAccomodationReviewService {

	public static final int LATEST_ACCOMODATION_REVIEWS_LIMIT = 4;

	public AccomodationReviewModel addNewAccomodationReview(final AccomodationReviewModel accomodationToAdd);

	public AccomodationReviewModel findAccomodationById(final Integer accomodationReviewId);

	public AccomodationReviewModel udpateAccomodationReview(final AccomodationReviewModel accomodationToUpdate);

	public void deleteAccomodationReviewById(final Integer accomodationReviewId);

	public List<AccomodationReviewModel> findAllAccomodationReviews(final String regNumber);

	public List<AccomodationReviewModel> findByUserId(final Integer userId);

	public Double getAccomodationReviewAverageStars(final String regNumber);

	public List<AccomodationReviewModel> findLatestAccomodationReviews(final String regNumber);

	public Integer countAccomodationReviewFromAccomodation(final String regNumber);

}
