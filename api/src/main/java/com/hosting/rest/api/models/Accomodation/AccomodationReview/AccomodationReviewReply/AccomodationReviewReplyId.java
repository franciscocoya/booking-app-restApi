package com.hosting.rest.api.models.Accomodation.AccomodationReview.AccomodationReviewReply;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class AccomodationReviewReplyId implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "ID_USER_OWNER")
	private Integer idUserHost;

	@Column(name = "ID_ACCOMODATION_REVIEW")
	private Integer idAccomodationReview;

}
