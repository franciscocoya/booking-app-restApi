package com.hosting.rest.api.services.Accomodation.AccomodationService;

import java.util.List;

import com.hosting.rest.api.models.Accomodation.AccomodationService.AccomodationServiceModel;

public interface IAccomodationServiceService {

	AccomodationServiceModel addNewAccomodationService(final AccomodationServiceModel accomodationService);

	void deleteAccomodationServiceById(final String accomodationRegNumber, final Integer accomodationServiceId);
	
	AccomodationServiceModel updateAccomodationService(final AccomodationServiceModel accomodationService);

	List<AccomodationServiceModel> findAllAccomodationServicesFromAccomodation(final String regNumber);
}
