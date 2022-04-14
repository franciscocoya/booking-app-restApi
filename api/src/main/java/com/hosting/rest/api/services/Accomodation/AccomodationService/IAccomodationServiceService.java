package com.hosting.rest.api.services.Accomodation.AccomodationService;

import java.util.List;

import com.hosting.rest.api.models.Accomodation.AccomodationService.AccomodationServiceModel;

public interface IAccomodationServiceService {

	public AccomodationServiceModel addNewAccomodationService(final AccomodationServiceModel accomodationService);

	public AccomodationServiceModel getAccomodationServiceById(final Integer accomodationServiceId);

	public void deleteAccomodationServiceById(final Integer accomodationServiceId);

	public AccomodationServiceModel updateAccomodationService(final Integer accomodationServiceId, final AccomodationServiceModel accomodationService);

	public List<AccomodationServiceModel> findAllAccomodationServicesFromAccomodation(final String regNumber);
}
