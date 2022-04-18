package com.hosting.rest.api.controllers.Accomodation.AccomodationReview.AccomodationReviewReply;

import static com.hosting.rest.api.Utils.AppUtils.isNotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hosting.rest.api.exceptions.IllegalArguments.IllegalArgumentsCustomException;
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
		if (!isNotNull(accomodationReviewReplyToAdd)) {
			throw new IllegalArgumentsCustomException(
					"Alguno de los parámetros de la respuesta de la valoración del alojamiento no es válido.");
		}

		return accomodationReviewReplyService.addNewAccomodationReviewReply(accomodationReviewReplyToAdd);
	}

	@GetMapping("r/{accomodationReviewId}")
	public AccomodationReviewReplyModel findByAccomodationReviewId(
			@PathVariable(name = "accomodationReviewId") final String accomodationReviewId) {

		AccomodationReviewReplyModel accomodationReviewReplyToReturn = null;

		try {
			accomodationReviewReplyToReturn = accomodationReviewReplyService
					.findByReviewId(Integer.parseInt(accomodationReviewId));

		} catch (NumberFormatException nfe) {
			throw new IllegalArgumentsCustomException("El id de la valoración del alojamiento no es válido.");
		}

		return accomodationReviewReplyToReturn;
	}

	@DeleteMapping("{accomodationReviewId}")
	public void deleteByAccomodationReviewReply(
			@PathVariable(name = "accomodationReviewId") final String accomodationReviewId) {

		try {
			accomodationReviewReplyService.deleteAccomodationReviewReplyById(Integer.parseInt(accomodationReviewId));

		} catch (NumberFormatException nfe) {
			throw new IllegalArgumentsCustomException(
					"El id de la respuesta a la valoración del alojamiento no es válido.");
		}

	}
}
