package com.hosting.rest.api.repositories.Accomodation.AccomodationReview.AccomodationReviewReply;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hosting.rest.api.models.Accomodation.AccomodationReview.AccomodationReviewReply.AccomodationReviewReplyId;
import com.hosting.rest.api.models.Accomodation.AccomodationReview.AccomodationReviewReply.AccomodationReviewReplyModel;

@Repository
public interface IAccomodationReviewReplyRepository
		extends JpaRepository<AccomodationReviewReplyModel, AccomodationReviewReplyId> {

}
