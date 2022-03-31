package com.hosting.rest.api.controllers.Accomodation.AccomodationReview.AccomodationReviewReply;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hosting.rest.api.models.Accomodation.AccomodationReview.AccomodationReviewReply.AccomodationReviewReplyModel;
import com.hosting.rest.api.services.Accomodation.AccomodationReview.AccomodationReviewReply.AccomodationReviewReplyServiceImpl;

@RestController
@RequestMapping("/accomodations/reviews/reply")
public class AccomodationReviewReplyController {

	@Autowired
	private AccomodationReviewReplyServiceImpl accomodationReviewReplyService;

	@PostMapping("new")
	public AccomodationReviewReplyModel addNewAccomodationReviewReply(
			final AccomodationReviewReplyModel accomodationReviewReplyToAdd) {
		return accomodationReviewReplyService.addNewAccomodationReviewReply(accomodationReviewReplyToAdd);
	}

	@GetMapping("{replyId}")
	public AccomodationReviewReplyModel findByAccomodationReviewReplyId(
			@PathVariable(name = "replyId") final Integer accomodationReviewReplyId) {
		return accomodationReviewReplyService.findByReviewId(accomodationReviewReplyId);
	}

	@DeleteMapping("{replyId}")
	public void deleteByAccomodationReviewReply(
			@PathVariable(name = "replyId") final Integer accomodationReviewReplyId) {
		accomodationReviewReplyService.deleteAccomodationReviewReplyById(accomodationReviewReplyId);
	}
}
