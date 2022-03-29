package com.hosting.rest.api.repositories.Accomodation.AccomodationService;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hosting.rest.api.models.Accomodation.AccomodationService.AccomodationAccServiceId;
import com.hosting.rest.api.models.Accomodation.AccomodationService.AccomodationServiceModel;

@Repository
public interface IAccomodationServiceRepository
		extends JpaRepository<AccomodationServiceModel, AccomodationAccServiceId> {

}
