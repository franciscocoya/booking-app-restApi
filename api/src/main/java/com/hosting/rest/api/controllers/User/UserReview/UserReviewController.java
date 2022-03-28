package com.hosting.rest.api.controllers.User.UserReview;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hosting.rest.api.models.User.HostReviewModel;
import com.hosting.rest.api.services.User.UserReview.UserReviewServiceImpl;

@RestController
@RequestMapping(name = "/users/reviews")
public class UserReviewController {

	@Autowired
	private UserReviewServiceImpl userReviewService;

	@PostMapping("new")
	public HostReviewModel addNewHostReview(@RequestBody HostReviewModel userReviewToAdd) {
		return userReviewService.addNewUserReview(userReviewToAdd);
	}

	@PutMapping("{userId}")
	public HostReviewModel updateHostReview(@PathVariable(name = "userId") Integer userId,
			@RequestBody HostReviewModel userReviewToAdd) {
		return null;
	}

	@DeleteMapping("{userId}")
	public void deleteHostReview(@PathVariable(name = "userId") Integer userId) {
		userReviewService.deleteUserReview(userId);
	}

	@GetMapping("{userId}")
	public List<HostReviewModel> findUserReviewsOfHostUser(@PathVariable(name = "userId") Integer userId) {
		return userReviewService.findByUserId(userId);
	}
}
