package com.hosting.rest.api.controllers.Accomodation.SavedAccomodation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hosting.rest.api.exceptions.IllegalArguments.IllegalArgumentsCustomException;
import com.hosting.rest.api.models.Accomodation.SavedAccomodation.SavedAccomodationModel;
import com.hosting.rest.api.services.Accomodation.SavedAccomodation.SavedAccomodationServiceImpl;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin(origins = { "*" })
@RequestMapping("accomodations/saved")
@Slf4j
public class SavedAccomodationController {

	@Autowired
	private SavedAccomodationServiceImpl savedAccomodationService;

	@PreAuthorize("hasRole('ROLE_BASE_USER') or hasRole('ROLE_HOST_USER') or hasRole('ROLE_ADMIN_USER')")
	@PostMapping("new")
	public SavedAccomodationModel addNewSavedAccomodation(
			@RequestBody final SavedAccomodationModel savedAccomodationToCreate) {
		return savedAccomodationService.addNewSavedAccomodation(savedAccomodationToCreate);
	}

	@PreAuthorize("hasRole('ROLE_BASE_USER') or hasRole('ROLE_HOST_USER') or hasRole('ROLE_ADMIN_USER')")
	@PostMapping("{regNumber}/{idUser}/new")
	public SavedAccomodationModel addNewSavedAccomodationByRegNumber(
			@PathVariable(name = "regNumber") final String regNumber,
			@PathVariable(name = "idUser") final String userId) {
		SavedAccomodationModel savedAccomodationToReturn = null;

		try {
			savedAccomodationToReturn = savedAccomodationService.addNewSavedAccomodationByRegNumber(regNumber,
					Integer.parseInt(userId));
			
		} catch (NumberFormatException nfe) {
			log.error("El id de usuario no es un número.");
			throw new IllegalArgumentsCustomException("El id de usuario no es un número.");
		}

		return savedAccomodationToReturn;
	}

	//@PreAuthorize("hasRole('ROLE_BASE_USER') or hasRole('ROLE_HOST_USER') or hasRole('ROLE_ADMIN_USER')")
	@GetMapping("{savedAccomodationId}")
	public SavedAccomodationModel getSavedAccomodationById(
			@PathVariable(value = "savedAccomodationId") final String savedAccomodationId) {
		SavedAccomodationModel savedAccomodationToReturn = null;

		try {
			savedAccomodationToReturn = savedAccomodationService
					.getSavedAccomodationById(Integer.parseInt(savedAccomodationId));

		} catch (NumberFormatException nfe) {
			log.error("El id del alojamiento guardado introducido no es un número.");
			throw new IllegalArgumentsCustomException("El id del alojamiento guardado introducido no es un número.");
		}

		return savedAccomodationToReturn;
	}
	
	@GetMapping("{regNumber}/{idUser}")
	public SavedAccomodationModel getSavedAccomodationByRegNumberAndUserId(
			@PathVariable(name = "regNumber") final String regNumber,
			@PathVariable(name = "idUser") final String userId) {
		SavedAccomodationModel savedAccomodationToReturn = null;

		try {
			savedAccomodationToReturn = savedAccomodationService
					.getSavedAccomodationByRegNumberAndUserId(regNumber, Integer.parseInt(userId));

		} catch (NumberFormatException nfe) {
			log.error("El id del alojamiento guardado introducido no es un número.");
			throw new IllegalArgumentsCustomException("El id del alojamiento guardado introducido no es un número.");
		}

		return savedAccomodationToReturn;
	}

	@PreAuthorize("hasRole('ROLE_BASE_USER') or hasRole('ROLE_HOST_USER') or hasRole('ROLE_ADMIN_USER')")
	@DeleteMapping("{savedAccomodationId}")
	public void deleteSavedAccomodationById(
			@PathVariable(value = "savedAccomodationId") final String savedAccomodationId) {
		try {
			savedAccomodationService.deleteSavedAccomodation(Integer.parseInt(savedAccomodationId));

		} catch (NumberFormatException nfe) {
			log.error("El id del alojamiento guardado introducido no es un número.");
			throw new IllegalArgumentsCustomException("El id del alojamiento guardado introducido no es un número.");
		}
	}

	@PreAuthorize("hasRole('ROLE_BASE_USER') or hasRole('ROLE_HOST_USER') or hasRole('ROLE_ADMIN_USER')")
	@DeleteMapping("{regNumber}/{userId}")
	public void deleteSavedAccomodationByRegNumberAndUserId(@PathVariable(name = "regNumber") final String regNumber,
			@PathVariable(name = "userId") final String userId) {
		try {
			savedAccomodationService.deleteSavedAccomodationByRegNumberAndUserId(regNumber, Integer.parseInt(userId));

		} catch (NumberFormatException nfe) {
			log.error("El id de usuario no es un número.");
			throw new IllegalArgumentsCustomException("El id de usuario no es un número.");
		}
	}

	@PreAuthorize("hasRole('ROLE_BASE_USER') or hasRole('ROLE_HOST_USER') or hasRole('ROLE_ADMIN_USER')")
	@GetMapping("users/{userId}")
	public List<SavedAccomodationModel> findAllByUserId(@PathVariable(value = "userId") final String userId) {
		List<SavedAccomodationModel> savedAccomodationsToReturn = null;

		try {
			savedAccomodationsToReturn = savedAccomodationService
					.findAllSavedAccomodationsByUserId(Integer.parseInt(userId));

		} catch (NumberFormatException nfe) {
			log.error("El id de usuario [ " + userId + " ] no es un número.");
			throw new IllegalArgumentsCustomException("El id de usuario [ " + userId + " ] no es un número.");
		}

		return savedAccomodationsToReturn;
	}
}
