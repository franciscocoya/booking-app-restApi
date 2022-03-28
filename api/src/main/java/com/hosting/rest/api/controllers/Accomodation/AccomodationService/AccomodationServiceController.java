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
	public AccomodationServiceModel addNewAccomodationService(@RequestBody AccomodationServiceModel accomodationServiceModel) {
		return accomodationServiceService.addNewAccomodationService(accomodationServiceModel);
	}
	
	@GetMapping("{accomodationServiceId}")
	public AccomodationServiceModel getAccomodationServiceById(@PathVariable(name = "accomodationServiceId") Integer accomodationServiceId) {
		return accomodationServiceService.getAccomodationServiceById(accomodationServiceId);
	}
	
	@DeleteMapping("{accomodationServiceId}")
	public void deleteAccomodationServiceById(Integer accomodationServiceId) {
		accomodationServiceService.deleteAccomodationServiceById(accomodationServiceId);
	}
	
	@PutMapping("{accomodationServiceId}")
	public AccomodationServiceModel updateAccomodationService(@RequestBody AccomodationServiceModel accomodationService) {
		return accomodationServiceService.updateAccomodationService(accomodationService);
	}
	
	@GetMapping("all")
	public List<AccomodationServiceModel> listAllAccomodationServices() {
		return accomodationServiceService.listAllAccomodationServices();
	}
	
	@GetMapping("{accomodationId}/all")
	public List<AccomodationServiceModel> listAllAccomodationServicesFromAccomodation(String regNumber) {
		return accomodationServiceService.listAllAccomodationServicesFromAccomodation(regNumber);
	}
	
	
}
