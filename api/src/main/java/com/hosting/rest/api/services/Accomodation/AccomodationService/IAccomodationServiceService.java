package com.hosting.rest.api.services.Accomodation.AccomodationService;

import java.util.List;

import com.hosting.rest.api.models.Accomodation.AccomodationService.AccomodationServiceModel;

public interface IAccomodationServiceService {

	AccomodationServiceModel addNewAccomodationService(AccomodationServiceModel accomodationService);
	
	AccomodationServiceModel getAccomodationServiceById(Integer accomodationServiceId);

	void deleteAccomodationServiceById(Integer accomodationId);
	
	AccomodationServiceModel updateAccomodationService(AccomodationServiceModel accomodationService);
	
	List<AccomodationServiceModel> listAllAccomodationServices();

	List<AccomodationServiceModel> listAllAccomodationServicesFromAccomodation(String regNumber);
}
