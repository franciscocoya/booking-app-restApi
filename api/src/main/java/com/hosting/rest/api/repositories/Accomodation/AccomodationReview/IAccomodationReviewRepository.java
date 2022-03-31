package com.hosting.rest.api.repositories.Accomodation.AccomodationReview;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hosting.rest.api.models.Accomodation.AccomodationReviewModel;

@Repository
public interface IAccomodationReviewRepository extends JpaRepository<AccomodationReviewModel, Integer> {

}
