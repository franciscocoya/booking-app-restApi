package com.hosting.rest.api.controllers.Accomodation.AccomodationLocation;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hosting.rest.api.exceptions.IllegalArguments.IllegalArgumentsCustomException;
import com.hosting.rest.api.models.Accomodation.AccomodationLocationModel;
import com.hosting.rest.api.services.Accomodation.AccomodationLocation.AccomodationLocationServiceImpl;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Francisco Coya Abajo
 * @version v1.0.0
 * @apiNote Controlador de ubicaciones de alojamientos.
 */
@RestController
@CrossOrigin(origins = { "*" })
@RequestMapping("/accomodations/locations")
@Slf4j
public class AccomodationLocationController {

	@Autowired
	private AccomodationLocationServiceImpl accomodationLocationService;

	@PreAuthorize("hasRole('ROLE_HOST_USER') or hasRole('ROLE_ADMIN_USER')")
	@PostMapping("new")
	public AccomodationLocationModel addNewAccomodationLocation(
			@Valid @RequestBody final AccomodationLocationModel accomodationLocationToAdd) {
		return accomodationLocationService.addNewAccomodationLocation(accomodationLocationToAdd);
	}

	@GetMapping("{accomodationLocationId}")
	public AccomodationLocationModel findAccomodationLocationById(
			@PathVariable(name = "accomodationLocationId") final String accomodationLocationId) {
		AccomodationLocationModel accomodationLocationToReturn = null;

		try {
			accomodationLocationToReturn = accomodationLocationService
					.getAccomodationLocationById(Integer.parseInt(accomodationLocationId));

		} catch (NumberFormatException nfe) {
			log.error("El id de ubicación a añadir no es un número.");
			throw new IllegalArgumentsCustomException("El id de ubicación a añadir no es un número.");
		}

		return accomodationLocationToReturn;
	}
}
