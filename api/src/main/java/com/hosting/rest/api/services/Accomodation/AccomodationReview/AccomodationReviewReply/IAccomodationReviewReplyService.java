package com.hosting.rest.api.services.Accomodation.AccomodationReview.AccomodationReviewReply;

import com.hosting.rest.api.models.Accomodation.AccomodationReview.AccomodationReviewReply.AccomodationReviewReplyModel;

public interface IAccomodationReviewReplyService {

	public AccomodationReviewReplyModel addNewAccomodationReviewReply(
			final AccomodationReviewReplyModel accomodationReviewReplyToAdd);

	public void deleteAccomodationReviewReplyById(final Integer accomodationReviewReplyId);

	public AccomodationReviewReplyModel findByReviewId(final Integer accomodationReviewId);
}
