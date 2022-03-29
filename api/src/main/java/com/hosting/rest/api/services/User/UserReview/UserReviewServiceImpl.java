package com.hosting.rest.api.services.User.UserReview;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hosting.rest.api.models.User.HostReviewModel;
import com.hosting.rest.api.repositories.User.UserReview.IUserReviewRepository;

@Service
public class UserReviewServiceImpl implements IUserReviewService {

	@Autowired
	private IUserReviewRepository userReviewRepo;

	@Override
	public HostReviewModel addNewUserReview(final HostReviewModel newUserReview) {
		return userReviewRepo.save(newUserReview);
	}

	@Override
	public HostReviewModel updateUserReview(final Integer userId, final HostReviewModel userReviewToUpdate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteUserReview(final Integer userId) {
		userReviewRepo.deleteById(userId);
	}

	@Override
	public List<HostReviewModel> findByUserId(final Integer userId) {
		return userReviewRepo.findByUserId(userId);
	}

	@Override
	public List<HostReviewModel> findAllReviews() {
		return userReviewRepo.findAll();
	}

}
