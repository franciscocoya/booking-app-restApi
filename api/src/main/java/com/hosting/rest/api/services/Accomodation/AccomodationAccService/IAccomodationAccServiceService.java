package com.hosting.rest.api.services.Accomodation.AccomodationAccService;

import com.hosting.rest.api.models.Accomodation.AccomodationService.AccomodationAccServiceModel;

public interface IAccomodationAccServiceService {

	public AccomodationAccServiceModel addNewAccomodationServiceToAccomodation(final String accomodationRegNumber,
			final Integer serviceId);

	public void deleteServiceFromAccomodation(final Integer accomodationServiceId, final String regNumber);

	public AccomodationAccServiceModel getAccomodationAccServiceById(final Integer accomodationServiceId,
			final String regNumber);
}
