package com.hosting.rest.api.controllers.Accomodation.AccomodationService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hosting.rest.api.models.Accomodation.AccomodationService.AccomodationServiceModel;
import com.hosting.rest.api.services.Accomodation.AccomodationService.IAccomodationServiceServiceImpl;

@RestController
@RequestMapping("/accomodations/services")
public class AccomodationServiceController {

	@Autowired
	private IAccomodationServiceServiceImpl accomodationServiceService;
	
	@PostMapping("new")
	public AccomodationServiceModel addNewAccomodationService(@RequestBody final AccomodationServiceModel accomodationServiceModel) {
		return accomodationServiceService.addNewAccomodationService(accomodationServiceModel);
	}
	
	@GetMapping("{accomodationServiceId}")
	public AccomodationServiceModel getAccomodationServiceById(@PathVariable(name = "accomodationServiceId") final Integer accomodationServiceId) {
//		return accomodationServiceService.getAccomodationServiceById(accomodationServiceId);
		return null;
	}
	
	@DeleteMapping("{accomodationServiceId}")
	public void deleteAccomodationServiceById(final Integer accomodationServiceId) {
//		accomodationServiceService.deleteAccomodationServiceById(accomodationServiceId);
	}
	
	@PutMapping("{accomodationServiceId}")
	public AccomodationServiceModel updateAccomodationService(@RequestBody final AccomodationServiceModel accomodationService) {
		return accomodationServiceService.updateAccomodationService(accomodationService);
	}
	
	@GetMapping("all")
	public List<AccomodationServiceModel> listAllAccomodationServices() {
		return accomodationServiceService.listAllAccomodationServices();
	}
	
	@GetMapping("{regNumber}/all")
	public List<AccomodationServiceModel> listAllAccomodationServicesFromAccomodation(@PathVariable(name = "regNumber") final String regNumber) {
		return accomodationServiceService.listAllAccomodationServicesFromAccomodation(regNumber);
	}
	
	
}
