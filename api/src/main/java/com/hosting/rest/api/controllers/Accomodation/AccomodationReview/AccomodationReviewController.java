package com.hosting.rest.api.controllers.Accomodation.AccomodationReview;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hosting.rest.api.exceptions.IllegalArguments.IllegalArgumentsCustomException;
import com.hosting.rest.api.models.Accomodation.AccomodationReview.AccomodationReviewModel;
import com.hosting.rest.api.services.Accomodation.AccomodationReview.AccomodationReviewServiceImpl;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author Francisco Coya Abajo
 * @version v1.0.0
 * @apiNote Controlador de valoraciones de un alojamiento.
 * 
 */
@RestController
@CrossOrigin(origins = {"*"})
@RequestMapping("accomodations/reviews")
@Slf4j
public class AccomodationReviewController {

	@Autowired
	private AccomodationReviewServiceImpl accomodationReviewService;

	@PreAuthorize("hasRole('ROLE_HOST_USER') or hasRole('ROLE_ADMIN_USER')")
	@PostMapping("new")
	public AccomodationReviewModel addNewAccomodationReview(
			@RequestBody final AccomodationReviewModel accomodationReviewToAdd) {
		return accomodationReviewService.addNewAccomodationReview(accomodationReviewToAdd);
	}

	@PreAuthorize("hasRole('ROLE_HOST_USER') or hasRole('ROLE_ADMIN_USER')")
	@PutMapping("{accomodationReviewId}")
	public AccomodationReviewModel updateAccomodationReview(
			@PathVariable(value = "accomodationReviewId") final String accomodationReviewId,
			@Valid @RequestBody AccomodationReviewModel accomodationReviewToUpdate) {
		AccomodationReviewModel accomodationReviewToReturn = null;

		try {
			accomodationReviewToReturn = accomodationReviewService
					.udpateAccomodationReview(Integer.parseInt(accomodationReviewId), accomodationReviewToUpdate);

		} catch (NumberFormatException nfe) {
			log.error("El id de la valoración del alojamiento [ " + accomodationReviewId + " ] no es válida.");
			throw new IllegalArgumentsCustomException(
					"El id de la valoración del alojamiento [ " + accomodationReviewId + " ] no es válida.");
		}

		return accomodationReviewToReturn;
	}

//	@PreAuthorize("hasRole('ROLE_HOST_USER') or hasRole('ROLE_ADMIN_USER')")
	@DeleteMapping("{accomodationReviewId}")
	public void deleteAccomodationReviewById(final String accomodationReviewId) {
		try {
			accomodationReviewService.deleteAccomodationReviewById(Integer.parseInt(accomodationReviewId));

		} catch (NumberFormatException nfe) {
			log.error("El id de la valoración del alojamiento [ " + accomodationReviewId + " ] no es válida.");
			throw new IllegalArgumentsCustomException(
					"El id de la valoración del alojamiento [ " + accomodationReviewId + " ] no es válida.");
		}
	}

//	@PreAuthorize("hasRole('ROLE_BASE_USER') or hasRole('ROLE_HOST_USER') or hasRole('ROLE_ADMIN_USER')")
	@GetMapping("{registerNumber}")
	public List<AccomodationReviewModel> listAccomodationReviewsByAccomodationId(
			@PathVariable(value = "registerNumber") final String regNumber) {
		return accomodationReviewService.findAllAccomodationReviews(regNumber);
	}

	@PreAuthorize("hasRole('ROLE_BASE_USER') or hasRole('ROLE_HOST_USER') or hasRole('ROLE_ADMIN_USER')")
	@GetMapping("/u/{userId}")
	public List<AccomodationReviewModel> findAllAccomodationReviewsByUserId(
			@PathVariable(value = "userId") final String userId) {
		List<AccomodationReviewModel> userReviews = null;

		try {
			userReviews = accomodationReviewService.findByUserId(Integer.parseInt(userId));

		} catch (NumberFormatException nfe) {
			log.error("El id de usuario [ " + userId + " ] introducido no es un valor numérico.");
			throw new IllegalArgumentsCustomException(
					"El id de usuario [ " + userId + " ] introducido no es un valor numérico.");
		}
		return userReviews;
	}

//	@PreAuthorize("hasRole('ROLE_BASE_USER') or hasRole('ROLE_HOST_USER') or hasRole('ROLE_ADMIN_USER')")
	@GetMapping("{registerNumber}/stars")
	public Double getAccomodationReviewStarsAverage(@PathVariable(value = "registerNumber") final String regNumber) {
		return accomodationReviewService.getAccomodationReviewAverageStars(regNumber);
	}

//	@PreAuthorize("hasRole('ROLE_BASE_USER') or hasRole('ROLE_HOST_USER') or hasRole('ROLE_ADMIN_USER')")
	@GetMapping("{registerNumber}/latest")
	public List<AccomodationReviewModel> findLastAccomodationReviews(
			@PathVariable(value = "registerNumber") final String regNumber) {
		return accomodationReviewService.findLatestAccomodationReviews(regNumber);
	}

}
