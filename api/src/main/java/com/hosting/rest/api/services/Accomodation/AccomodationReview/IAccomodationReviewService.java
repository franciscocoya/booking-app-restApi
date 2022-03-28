package com.hosting.rest.api.services.Accomodation.AccomodationReview;

import java.util.List;

import com.hosting.rest.api.models.Accomodation.AccomodationReviewModel;

public interface IAccomodationReviewService {

	AccomodationReviewModel addNewAccomodationReview(AccomodationReviewModel accomodationToAdd);

	AccomodationReviewModel getAccomodationReviewById(Integer accomodationReviewId);

	AccomodationReviewModel udpateAccomodationReview(AccomodationReviewModel accomodationToUpdate);

	void deleteAccomodationReviewById(Integer accomodationReviewId);

	List<AccomodationReviewModel> findAllAccomodationReviews(String regNumber);
}
