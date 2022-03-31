package com.hosting.rest.api.models.Accomodation.AccomodationReview.AccomodationReviewReply;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ACCOMODATION_REVIEW_REPLY")
public class AccomodationReviewReplyModel {

	@EmbeddedId
	private AccomodationReviewReplyId accomodationReviewReplyId;

	@Column(name = "CONTENT")
	private String content;
}
