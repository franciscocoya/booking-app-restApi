package com.hosting.rest.api.controllers.Accomodation.AccomodationCategory;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hosting.rest.api.exceptions.IllegalArguments.IllegalArgumentsCustomException;
import com.hosting.rest.api.models.Accomodation.AccomodationCategoryModel;
import com.hosting.rest.api.services.Accomodation.AccomodationCategory.AccomodationCategoryServiceImpl;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author Francisco Coya Abajo
 * @version v1.0.0
 * @apiNote Controlador de categorías de alojamiento de la aplicación.
 * 
 */
@RestController
@CrossOrigin(origins = { "*" })
@RequestMapping("accomodations/categories")
@Slf4j
public class AccomodationCategoryController {

	@Autowired
	private AccomodationCategoryServiceImpl accomodationCategoryService;

	@PostMapping("new")
	public AccomodationCategoryModel addNewCategory(
			@Valid @RequestBody final AccomodationCategoryModel accomodationCategoryToAdd) {
		return accomodationCategoryService.addNewAccomodationCategory(accomodationCategoryToAdd);
	}

	@PreAuthorize("hasRole('ROLE_HOST_USER') or hasRole('ROLE_ADMIN_USER')")
	@PutMapping("{categoryId}")
	public AccomodationCategoryModel updateAccomodationCategory(
			@PathVariable(name = "categoryId") final String accomodationCategoryId,
			@Valid @RequestBody final AccomodationCategoryModel accomodationCategoryToUpdate) {
		AccomodationCategoryModel accomodationCategoryToReturn = null;

		try {
			accomodationCategoryToReturn = accomodationCategoryService
					.updateAccomodationCategory(Integer.parseInt(accomodationCategoryId), accomodationCategoryToUpdate);

		} catch (NumberFormatException nfe) {
			log.error("El id de la categoría no es un número.");
			throw new IllegalArgumentsCustomException("El id de la categoría no es un número.");
		}

		return accomodationCategoryToReturn;
	}

	
	@DeleteMapping("{categoryId}")
	public void deleteAccomodationCategoryById(@PathVariable(name = "categoryId") final String accomodationCategoryId) {
		try {
			accomodationCategoryService.deleteAccomodationCategoryById(Integer.parseInt(accomodationCategoryId));

		} catch (NumberFormatException nfe) {
			log.error("El id de la categoría no es un número.");
			throw new IllegalArgumentsCustomException("El id de la categoría no es un número.");
		}
	}

	@GetMapping("all")
	public List<AccomodationCategoryModel> listAllAccomodationCategories() {
		return accomodationCategoryService.findAllAccomodationCategories();
	}

}
