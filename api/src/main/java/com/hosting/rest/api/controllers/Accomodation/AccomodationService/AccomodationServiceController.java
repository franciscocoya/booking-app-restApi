package com.hosting.rest.api.controllers.Accomodation.AccomodationService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hosting.rest.api.exceptions.IllegalArguments.IllegalArgumentsCustomException;
import com.hosting.rest.api.models.Accomodation.AccomodationService.AccomodationAccServiceModel;
import com.hosting.rest.api.models.Accomodation.AccomodationService.AccomodationServiceModel;
import com.hosting.rest.api.services.Accomodation.AccomodationAccService.AccomodationAccServiceServiceImpl;
import com.hosting.rest.api.services.Accomodation.AccomodationService.AccomodationServiceServiceImpl;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/accomodations/services")
@Slf4j
public class AccomodationServiceController {

	@Autowired
	private AccomodationServiceServiceImpl accomodationServiceService;

	@Autowired
	private AccomodationAccServiceServiceImpl accomodationAccServiceService;

	@PreAuthorize("hasRole('ROLE_HOST_USER') or hasRole('ROLE_ADMIN_USER')")
	@PostMapping("new")
	public AccomodationServiceModel addNewAccomodationService(
			@RequestBody final AccomodationServiceModel accomodationServiceModel) {
		return accomodationServiceService.addNewAccomodationService(accomodationServiceModel);
	}

	@PreAuthorize("hasRole('ROLE_HOST_USER') or hasRole('ROLE_ADMIN_USER')")
	@DeleteMapping
	public void deleteAccomodationServiceById(@RequestParam("service") final String accomodationServiceId) {

		try {
			accomodationServiceService.deleteAccomodationServiceById(Integer.parseInt(accomodationServiceId));
			
		} catch (NumberFormatException nfe) {
			log.error("El id del servicio de alojamiento [ " + accomodationServiceId + " ] no es válido.");
			throw new IllegalArgumentsCustomException(
					"El id del servicio de alojamiento [ " + accomodationServiceId + " ] no es válido.");
		}
	}

	@PreAuthorize("hasRole('ROLE_HOST_USER') or hasRole('ROLE_ADMIN_USER')")
	@PutMapping("{accomodationServiceId}")
	public AccomodationServiceModel updateAccomodationService(
			@PathVariable(name = "accomodationServiceId") final String accomodationServiceId,
			@RequestBody final AccomodationServiceModel accomodationService) {

		AccomodationServiceModel accomodationServiceUpdated = null;

		try {
			accomodationServiceUpdated = accomodationServiceService
					.updateAccomodationService(Integer.parseInt(accomodationServiceId), accomodationService);

		} catch (NumberFormatException nfe) {
			log.error("El id del servicio de alojamiento [ " + accomodationServiceId + " ] no es un número.");
			throw new IllegalArgumentsCustomException(
					"El id del servicio de alojamiento [ " + accomodationServiceId + " ] no es un número.");
		}
		return accomodationServiceUpdated;
	}

	@PreAuthorize("hasRole('ROLE_HOST_USER') or hasRole('ROLE_ADMIN_USER')")
	@GetMapping("{accomodationServiceId}")
	public AccomodationServiceModel getAccomodationServiceById(
			@PathVariable(name = "accomodationServiceId") final String accomodationServiceId) {
		AccomodationServiceModel accomodationServiceToReturn = null;

		try {
			accomodationServiceToReturn = accomodationServiceService
					.getAccomodationServiceById(Integer.parseInt(accomodationServiceId));

		} catch (NumberFormatException nfe) {
			log.error("El id del servicio del alojamiento [ " + accomodationServiceId + " ] no es un número.");
			throw new IllegalArgumentsCustomException(
					"El id del servicio del alojamiento [ " + accomodationServiceId + " ] no es un número.");
		}

		return accomodationServiceToReturn;
	}


	@PreAuthorize("hasRole('ROLE_HOST_USER') or hasRole('ROLE_ADMIN_USER')")
	@PostMapping("{regNumber}/new")
	public AccomodationAccServiceModel addNewServiceToAccomodation(
			@PathVariable(name = "regNumber") final String regNumber,
			@RequestBody final AccomodationServiceModel accomodationServiceToAdd) {

		return accomodationAccServiceService.addNewAccomodationServiceToAccomodation(regNumber,
				accomodationServiceToAdd);
	}

	@PreAuthorize("hasRole('ROLE_BASE_USER') or hasRole('ROLE_HOST_USER') or hasRole('ROLE_ADMIN_USER')")
	@GetMapping("{regNumber}/all")
	public List<AccomodationServiceModel> listAllAccomodationServicesFromAccomodation(
			@PathVariable(name = "regNumber") final String regNumber) {
		return accomodationServiceService.findAllAccomodationServicesFromAccomodation(regNumber);
	}

}
