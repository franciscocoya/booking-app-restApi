package com.hosting.rest.api.repositories.User.UserReview;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hosting.rest.api.models.User.HostReviewModel;

@Repository
public interface IUserReviewRepository extends JpaRepository<HostReviewModel, Integer> {

	@Query("select hr from HostReviewModel hr inner join hr.idUserA u where u.id = :userId")
	public List<HostReviewModel> findByUserId(@Param(value = "userId") Integer userId);
}
