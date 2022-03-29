package com.hosting.rest.api.controllers.Accomodation.SavedAccomodation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hosting.rest.api.models.Accomodation.SavedAccomodation.SavedAccomodationModel;
import com.hosting.rest.api.services.Accomodation.SavedAccomodation.SavedAccomodationServiceImpl;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("accomodations/saved")
public class SavedAccomodationController {
	
	@Autowired
	private SavedAccomodationServiceImpl savedAccomodationService;
	
	@PostMapping("new")
	public SavedAccomodationModel addNewSavedAccomodation(@RequestBody SavedAccomodationModel savedAccomodationToCreate) {
		return savedAccomodationService.addNewSavedAccomodation(savedAccomodationToCreate);
	}
	
	@GetMapping("{savedAccomodationId}")
	public SavedAccomodationModel getSavedAccomodationById(@PathVariable(name = "savedAccomodationId") Integer savedAccomodationId) {
		return savedAccomodationService.getSavedAccomodationById(savedAccomodationId);
	}
	
	@DeleteMapping("{savedAccomodationId}")
	public void deleteSavedAccomodationById(@PathVariable(name = "savedAccomodationId") Integer savedAccomodationId) {
		savedAccomodationService.deleteSavedAccomodation(savedAccomodationId);
	}
	
	// TODO: Listado de alojamientos guardados por un usuario (ID).
}