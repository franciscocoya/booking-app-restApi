package com.hosting.rest.api.repositories.Accomodation.AccomodationCategory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hosting.rest.api.models.Accomodation.AccomodationCategoryModel;

@Repository
public interface IAccomodationCategoryRepository extends JpaRepository<AccomodationCategoryModel, Integer> {

}
