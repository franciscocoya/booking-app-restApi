package com.hosting.rest.api.services.Accomodation.AccomodationLocation;

import com.hosting.rest.api.models.Accomodation.AccomodationLocationModel;

/**
 * @author Francisco Coya Abajo
 * @version v1.0.0
 * @apiNote Servicio de ubicaciones de alojamientos.
 */
public interface IAccomodationLocationService {

	public AccomodationLocationModel addNewAccomodationLocation(
			final AccomodationLocationModel accomodationLocationToAdd);
	
	public AccomodationLocationModel getAccomodationLocationById(final Integer accomodationLocationId);

}
