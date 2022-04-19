package com.hosting.rest.api.controllers.Accomodation;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hosting.rest.api.models.Accomodation.AccomodationModel;
import com.hosting.rest.api.services.Accomodation.AccomodationServiceImpl;

/**
 * @author Francisco Coya Abajo
 * @version v1.0.1
 * @apiNote Controlador de alojamientos.
 */
@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping("/accomodations")
public class AccomodationController {

	@Autowired
	private AccomodationServiceImpl accomodationService;

	@PostMapping("new")
	public AccomodationModel addNewAccomodation(@RequestBody final AccomodationModel accomodationModel) {
		return accomodationService.addNewAccomodation(accomodationModel);
	}

	@GetMapping("all")
	public List<AccomodationModel> getAllAccomodations() {
		return accomodationService.findAllAccomodations();
	}

	@GetMapping("{regNumber}")
	public AccomodationModel getAccomodationById(@PathVariable(value = "regNumber") final String regNumber) {
		return accomodationService.getAccomodationById(regNumber.trim());
	}

	@GetMapping("cities/{city}")
	public List<AccomodationModel> getAccomodationsByCity(@PathVariable(value = "city") final String city) {
		return accomodationService.findByCity(city.trim());
	}

	@GetMapping("nearby")
	public List<AccomodationModel> findNearbyAccomodations(@RequestParam(name = "lat") final BigDecimal latitude,
			@RequestParam(name = "lng") final BigDecimal longitude,
			@RequestParam(name = "distance") final Double distance) {

		return accomodationService.findByNearby(latitude, longitude, distance);
	}

	@GetMapping("category/{categoryName}")
	public List<AccomodationModel> findByCategory(@PathVariable(value = "categoryName") final String categoryToFind) {
		return accomodationService.findByCategory(categoryToFind);
	}

	@GetMapping("price")
	public List<AccomodationModel> findByPriceRange(@RequestParam(name = "minPrice") final BigDecimal minPrice,
			@RequestParam(name = "maxPrice") final BigDecimal maxPrice) {
		return accomodationService.findByPriceRange(minPrice, maxPrice);
	}

	@PatchMapping("{regNumber}")
	public AccomodationModel updateAccomodationById(@PathVariable(value = "regNumber") final String regNumber,
			@RequestBody final AccomodationModel accomodationToUpdate) {
		return accomodationService.updateAccomodationById(regNumber, accomodationToUpdate);
	}

	@DeleteMapping("{regNumber}")
	public void removeAccomodationById(@PathVariable(value = "regNumber") final String regNumber) {
		accomodationService.removeAccomodationById(regNumber);
	}

}
