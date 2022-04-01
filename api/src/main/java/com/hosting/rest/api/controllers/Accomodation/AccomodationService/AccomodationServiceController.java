package com.hosting.rest.api.controllers.Accomodation.AccomodationService;

import static com.hosting.rest.api.Utils.AppUtils.isStringNotBlank;
import static com.hosting.rest.api.Utils.AppUtils.isNotNull;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

@RestController
@RequestMapping("/accomodations/services")
public class AccomodationServiceController {

	@Autowired
	private AccomodationServiceServiceImpl accomodationServiceService;

	@Autowired
	private AccomodationAccServiceServiceImpl accomodationAccServiceService;

	@PostMapping("new")
	public AccomodationServiceModel addNewAccomodationService(
			@RequestBody final AccomodationServiceModel accomodationServiceModel) {
		return accomodationServiceService.addNewAccomodationService(accomodationServiceModel);
	}

	@DeleteMapping
	public void deleteAccomodationServiceById(@RequestParam("service") final String accomodationServiceId) {

		try {
			accomodationServiceService.deleteAccomodationServiceById(Integer.parseInt(accomodationServiceId));
		} catch (NumberFormatException nfe) {
			throw new IllegalArgumentsCustomException(
					"El id del servicio de alojamiento [ " + accomodationServiceId + " ] no es válido.");
		}
	}

	@PutMapping("{accomodationServiceId}")
	public AccomodationServiceModel updateAccomodationService(
			@RequestBody final AccomodationServiceModel accomodationService) {
		return accomodationServiceService.updateAccomodationService(accomodationService);
	}

	@GetMapping("{accomodationServiceId}")
	public AccomodationServiceModel getAccomodationServiceById(
			@PathVariable(name = "accomodationServiceId") final String accomodationServiceId) {
		AccomodationServiceModel accomodationServiceToReturn = null;

		try {
			accomodationServiceToReturn = accomodationServiceService
					.getAccomodationServiceById(Integer.parseInt(accomodationServiceId));

		} catch (NumberFormatException nfe) {
			throw new IllegalArgumentsCustomException(
					"El id del servicio del alojamiento [ " + accomodationServiceId + " ] no es válido.");
		}

		return accomodationServiceToReturn;
	}

	@PostMapping("{regNumber}/new")
	public AccomodationAccServiceModel addNewServiceToAccomodation(
			@PathVariable(name = "regNumber") final String regNumber,
			@RequestBody AccomodationServiceModel accomodationServiceToAdd) {
		if (!isStringNotBlank(regNumber)) {
			throw new IllegalArgumentsCustomException(
					"El número de registro de alojamiento está vacío o no es válido.");
		}

		if (!isNotNull(accomodationServiceToAdd)) {
			throw new IllegalArgumentsCustomException(
					"Alguno de los valores del servicio del alojamiento añadir no es válido.");
		}

		// TODO: Comprobar que el servicio existe

		return accomodationAccServiceService.addNewAccomodationServiceToAccomodation(regNumber,
				accomodationServiceToAdd);
	}

	@GetMapping("{regNumber}/all")
	public List<AccomodationServiceModel> listAllAccomodationServicesFromAccomodation(
			@PathVariable(name = "regNumber") final String regNumber) {
		return accomodationServiceService.findAllAccomodationServicesFromAccomodation(regNumber);
	}

	@GetMapping("{regNumber}/{accomodationServiceId}")
	public List<AccomodationServiceModel> listAllAccomodationServicesFromAccomodation(
			@PathVariable(name = "regNumber") final String regNumber,
			@PathVariable(name = "accomodationServiceId") final Integer accomodationServiceId) {

		// TODO: Crear el método en el servicio
		return null;
		// return accomodationServiceService.;
	}

}
