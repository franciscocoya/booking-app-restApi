package com.hosting.rest.api.services.Accomodation.AccomodationAccService;

import com.hosting.rest.api.models.Accomodation.AccomodationService.AccomodationAccServiceModel;
import com.hosting.rest.api.models.Accomodation.AccomodationService.AccomodationServiceModel;

public interface IAccomodationAccServiceService {

	public AccomodationAccServiceModel addNewAccomodationServiceToAccomodation(final String accomodationRegNumber,
			final AccomodationServiceModel accomodationServiceToAdd);

	public void deleteServiceFromAccomodation(final Integer accomodationServiceId, final String regNumber);

	public AccomodationAccServiceModel getAccomodationAccServiceById(final Integer accomodationServiceId,
			final String regNumber);
}
