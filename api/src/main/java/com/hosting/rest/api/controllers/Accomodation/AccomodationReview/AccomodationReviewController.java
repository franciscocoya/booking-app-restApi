package com.hosting.rest.api.controllers.Accomodation.AccomodationReview;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hosting.rest.api.models.Accomodation.AccomodationReviewModel;
import com.hosting.rest.api.services.Accomodation.AccomodationReview.AccomodationReviewServiceImpl;

/**
 * @author Francisco Coya Abajo
 * @version v1.0.0
 * @apiNote Controlador de valoraciones de un alojamiento.
 */
@RestController
@RequestMapping("accomodations/reviews")
public class AccomodationReviewController {

	@Autowired
	private AccomodationReviewServiceImpl accomodationReviewService;

	@PostMapping("new")
	public AccomodationReviewModel addNewAccomodationReview(
			@RequestBody final AccomodationReviewModel accomodationReviewToAdd) {
		return accomodationReviewService.addNewAccomodationReview(accomodationReviewToAdd);
	}

	@PutMapping("{accomodationReviewId}")
	public AccomodationReviewModel updateAccomodationReview(
			@PathVariable(value = "accomodationReviewId") final Integer accomodationReviewId,
			@Valid @RequestBody AccomodationReviewModel accomodationReviewToUpdate) {
		// TODO:
		return null;
	}

	@DeleteMapping("{accomodationReviewId}")
	public void deleteAccomodationReviewById(final Integer accomodationReviewId) {
		accomodationReviewService.deleteAccomodationReviewById(accomodationReviewId);
	}

	@GetMapping("{registerNumber}")
	public List<AccomodationReviewModel> listAccomodationReviewsByAccomodationId(
			@PathVariable(value = "registerNumber") final String regNumber) {
		return accomodationReviewService.findAllAccomodationReviews(regNumber);
	}

	@GetMapping("/u/{userId}/all")
	public List<AccomodationReviewModel> findAllAccomodationReviewsByUserId(
			@PathVariable(value = "userId") final Integer userId) {
		return accomodationReviewService.findByUserId(userId);
	}

	@GetMapping("{registerNumber}/stars")
	public Double getAccomodationReviewStarsAverage(@PathVariable(value = "registerNumber") final String regNumber) {
		return accomodationReviewService.getAccomodationReviewAverageStars(regNumber);
	}

	@GetMapping("{registerNumber}/latest")
	public List<AccomodationReviewModel> findLastAccomodationReviews(
			@PathVariable(value = "registerNumber") final String regNumber) {
		return accomodationReviewService.findLatestAccomodationReviews(regNumber);
	}

	// TODO: Obtener los 4 últimos usuarios que han valorado el alojamiento.

}
