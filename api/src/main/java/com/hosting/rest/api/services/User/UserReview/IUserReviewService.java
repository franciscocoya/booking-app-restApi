package com.hosting.rest.api.services.User.UserReview;

import java.util.List;

import com.hosting.rest.api.models.User.HostReviewModel;

public interface IUserReviewService {

	public HostReviewModel addNewUserReview(HostReviewModel newUserReview);

	public HostReviewModel updateUserReview(Integer userId, HostReviewModel userReviewToUpdate);

	void deleteUserReview(Integer userId);

	public List<HostReviewModel> findByUserId(Integer userId);
}
