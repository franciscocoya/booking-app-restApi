package com.hosting.rest.api.controllers.Accomodation.AccomodationService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.hosting.rest.api.models.Accomodation.AccomodationService.AccomodationServiceModel;
import com.hosting.rest.api.services.Accomodation.AccomodationService.AccomodationServiceServiceImpl;

@RestController
@RequestMapping("/accomodations/services")
public class AccomodationServiceController {

	@Autowired
	private AccomodationServiceServiceImpl accomodationServiceService;

	@PostMapping("new")
	public AccomodationServiceModel addNewAccomodationService(
			@RequestBody final AccomodationServiceModel accomodationServiceModel) {
		return accomodationServiceService.addNewAccomodationService(accomodationServiceModel);
	}

	@GetMapping("{accomodationServiceId}")
	public AccomodationServiceModel getAccomodationServiceById(
			@PathVariable(name = "accomodationServiceId") final Integer accomodationServiceId) {
//		return accomodationServiceService.getAccomodationServiceById(accomodationServiceId);
		return null;
	}

	@DeleteMapping
	public void deleteAccomodationServiceById(@RequestParam(name = "regNumber") final String accomodationRegNumber,
			@RequestParam("service") final Integer accomodationServiceId) {
		try {
			accomodationServiceService.deleteAccomodationServiceById(accomodationRegNumber, accomodationServiceId);

		} catch (IllegalArgumentException iae) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Alguno de los parámetros no es correcto.");
		}
	}

	@PutMapping("{accomodationServiceId}")
	public AccomodationServiceModel updateAccomodationService(
			@RequestBody final AccomodationServiceModel accomodationService) {
		return accomodationServiceService.updateAccomodationService(accomodationService);
	}

	@GetMapping("{regNumber}/all")
	public List<AccomodationServiceModel> listAllAccomodationServicesFromAccomodation(
			@PathVariable(name = "regNumber") final String regNumber) {
		return accomodationServiceService.findAllAccomodationServicesFromAccomodation(regNumber);
	}

}
