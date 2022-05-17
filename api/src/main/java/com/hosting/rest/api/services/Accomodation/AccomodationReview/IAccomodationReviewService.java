package com.hosting.rest.api.services.Accomodation.AccomodationReview;

import java.util.List;

import com.hosting.rest.api.models.Accomodation.AccomodationReview.AccomodationReviewModel;

public interface IAccomodationReviewService {

	public static final int LATEST_ACCOMODATION_REVIEWS_LIMIT = 4;

	public AccomodationReviewModel addNewAccomodationReview(final AccomodationReviewModel accomodationToAdd);

	public AccomodationReviewModel findAccomodationById(final Integer accomodationReviewId);

	public AccomodationReviewModel udpateAccomodationReview(final Integer accomodationReviewId,
			final AccomodationReviewModel accomodationToUpdate);

	public void deleteAccomodationReviewById(final Integer accomodationReviewId);

	public List<AccomodationReviewModel> findAllAccomodationReviews(final String regNumber);

	public List<AccomodationReviewModel> findAllSendAccomodationReviewsByUserId(final Integer userId); // Valoraciones escritas
	
	public List<AccomodationReviewModel> findAllReceivedAccomodationReviewsByUserId(final Integer userId); // Valoraciones recibidas

	public Double getAccomodationReviewAverageStars(final String regNumber);

	public List<AccomodationReviewModel> findLatestAccomodationReviews(final String regNumber);

}
