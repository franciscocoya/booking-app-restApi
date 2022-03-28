package com.hosting.rest.api.repositories.Accomodation.AccomodationReview;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hosting.rest.api.models.Accomodation.AccomodationReviewModel;

@Repository
public interface IAccomodationReviewRepository extends JpaRepository<AccomodationReviewModel, Integer> {
	
	@Query("select ar from AccomodationReviewModel ar inner join ar.idAccomodation ac where ac.registerNumber = :regNumber")
	public List<AccomodationReviewModel> findByAccomodationId(@Param(value = "regNumber") String regNumber);

}
