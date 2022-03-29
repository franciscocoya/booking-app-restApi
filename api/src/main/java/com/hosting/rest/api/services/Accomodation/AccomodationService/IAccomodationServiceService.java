package com.hosting.rest.api.services.Accomodation.AccomodationService;

import java.util.List;

import com.hosting.rest.api.models.Accomodation.AccomodationService.AccomodationServiceModel;

public interface IAccomodationServiceService {

	AccomodationServiceModel addNewAccomodationService(final AccomodationServiceModel accomodationService);
	
//	AccomodationServiceModel getAccomodationServiceById(Integer idAccomodationService);

//	void deleteAccomodationServiceById(Integer accomodationId);
	
	AccomodationServiceModel updateAccomodationService(final AccomodationServiceModel accomodationService);
	
	List<AccomodationServiceModel> listAllAccomodationServices();

	List<AccomodationServiceModel> listAllAccomodationServicesFromAccomodation(final String regNumber);
}
