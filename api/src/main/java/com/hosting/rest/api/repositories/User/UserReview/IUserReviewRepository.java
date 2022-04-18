package com.hosting.rest.api.repositories.User.UserReview;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hosting.rest.api.models.User.HostReviewModel;

@Repository
public interface IUserReviewRepository extends JpaRepository<HostReviewModel, Integer> {
}
