package com.hosting.rest.api.repositories.Accomodation.AccomodationLocation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hosting.rest.api.models.Accomodation.AccomodationLocationModel;

@Repository
public interface IAccomodationLocationRepository extends JpaRepository<AccomodationLocationModel, Integer> {
	
}
