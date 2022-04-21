package com.hosting.rest.api.controllers.User.UserReview;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hosting.rest.api.exceptions.IllegalArguments.IllegalArgumentsCustomException;
import com.hosting.rest.api.models.User.HostReviewModel;
import com.hosting.rest.api.services.User.UserReview.UserReviewServiceImpl;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(name = "/users/reviews")
@Slf4j
public class UserReviewController {

	@Autowired
	private UserReviewServiceImpl userReviewService;

	@PreAuthorize("hasRole('ROLE_BASE_USER') or hasRole('ROLE_HOST_USER') or hasRole('ROLE_ADMIN_USER')")
	@PostMapping("new")
	public HostReviewModel addNewHostReview(@RequestBody final HostReviewModel userReviewToAdd) {
		return userReviewService.addNewUserReview(userReviewToAdd);
	}

	@PreAuthorize("hasRole('ROLE_HOST_USER') or hasRole('ROLE_ADMIN_USER')")
	@PutMapping("{userId}")
	public HostReviewModel updateHostReview(@PathVariable(value = "userId") final String userId,
			@RequestBody HostReviewModel userReviewToAdd) {
		HostReviewModel hostReviewToReturn = null;

		try {
			userReviewToAdd = userReviewService.updateUserReview(Integer.parseInt(userId), userReviewToAdd);

		} catch (NumberFormatException nfe) {
			log.error("El id del usuario [ " + userId + " ] no es un número.");
			throw new IllegalArgumentsCustomException("El id del usuario [ " + userId + " ] no es un número.");
		}

		return hostReviewToReturn;
	}

	@PreAuthorize("hasRole('ROLE_HOST_USER') or hasRole('ROLE_ADMIN_USER')")
	@DeleteMapping("{userId}")
	public void deleteHostReview(@PathVariable(value = "userId") final String userId) {
		try {
			userReviewService.deleteUserReview(Integer.parseInt(userId));

		} catch (NumberFormatException nfe) {
			log.error("El id del usuario [ " + userId + " ] no es un número.");
			throw new IllegalArgumentsCustomException("El id del usuario [ " + userId + " ] no es un número.");
		}
	}

	@PreAuthorize("hasRole('ROLE_HOST_USER') or hasRole('ROLE_ADMIN_USER')")
	@GetMapping("{userId}")
	public List<HostReviewModel> findUserReviewsOfHostUser(@PathVariable(value = "userId") final String userId) {
		List<HostReviewModel> hosts = null;
		
		try {
			hosts = userReviewService.findByUserId(Integer.parseInt(userId));
			
		} catch (NumberFormatException nfe) {
			log.error("El id del usuario [ " + userId + " ] no es un número.");
			throw new IllegalArgumentsCustomException("El id del usuario [ " + userId + " ] no es un número.");
		}
		return hosts;
	}
}
