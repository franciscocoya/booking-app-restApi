package com.hosting.rest.api.services.User.UserReview;

import java.util.List;

import com.hosting.rest.api.models.User.HostReviewModel;

public interface IUserReviewService {

	public HostReviewModel addNewUserReview(final HostReviewModel newUserReview);

	public HostReviewModel updateUserReview(final Integer userId, final HostReviewModel userReviewToUpdate);

	public void deleteUserReview(final Integer userId);

	public List<HostReviewModel> findByUserId(final Integer userId);
}
