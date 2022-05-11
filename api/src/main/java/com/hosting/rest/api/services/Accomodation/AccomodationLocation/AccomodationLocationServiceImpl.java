package com.hosting.rest.api.services.Accomodation.AccomodationLocation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.hosting.rest.api.Utils.AppUtils.isIntegerValidAndPositive;
import static com.hosting.rest.api.Utils.AppUtils.isNotNull;
import static com.hosting.rest.api.Utils.ServiceParamValidator.validateParam;
import static com.hosting.rest.api.Utils.ServiceParamValidator.validateParamNotFound;

import com.hosting.rest.api.models.Accomodation.AccomodationLocationModel;
import com.hosting.rest.api.repositories.Accomodation.AccomodationLocation.IAccomodationLocationRepository;

/**
 * @author Francisco Coya Abajo
 * @version v1.0.0
 * @apiNote Implementación del servicio de ubicaciones de alojamientos.
 */
@Service
public class AccomodationLocationServiceImpl implements IAccomodationLocationService {

	@Autowired
	private IAccomodationLocationRepository accomodationLocationRepo;

	/**
	 * Crea una nueva ubicación de alojamiento.
	 * 
	 * @param accomodationLocationToAdd
	 * 
	 * @return
	 */
	@Override
	public AccomodationLocationModel addNewAccomodationLocation(
			final AccomodationLocationModel accomodationLocationToAdd) {

		// Validar ubicación
		validateParam(isNotNull(accomodationLocationToAdd),
				"Alguno de los datos introducidos para el alojamiento no es válido.");

		// Comprobar si existe
		validateParamNotFound(!accomodationLocationRepo.existsById(accomodationLocationToAdd.getId()),
				"La ubicación a crear ya existe");

		return accomodationLocationRepo.save(accomodationLocationToAdd);
	}

	/**
	 * Obtiene la ubicación de alojamiento con el id
	 * <code>accomodationLocationId</code>.
	 * 
	 * @param accomodationLocationId
	 * 
	 * @return
	 */
	@Override
	public AccomodationLocationModel getAccomodationLocationById(final Integer accomodationLocationId) {

		// Comprobar id
		validateParam(isIntegerValidAndPositive(accomodationLocationId),
				"El id de ubicación [ " + accomodationLocationId + " ] no es válido.");

		return accomodationLocationRepo.findById(accomodationLocationId).get();
	}

}
