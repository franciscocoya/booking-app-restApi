package com.hosting.rest.api.models.Accomodation.AccomodationReview.AccomodationReviewReply;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;

import com.hosting.rest.api.models.Accomodation.AccomodationReview.AccomodationReviewModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ACCOMODATION_REVIEW_REPLY")
public class AccomodationReviewReplyModel implements Serializable{

	private static final long serialVersionUID = 1330395931189086339L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Integer idAccomodationReviewReply;

	@OneToOne
	@JoinColumn(name = "ID_ACCOMODATION_REVIEW")
	private AccomodationReviewModel accomodationReview;

	@Column(name = "CONTENT")
	private String content;

	@Column(name = "CREATED_AT")
	@CreatedDate
	private LocalDateTime createdAt;
}
