package com.hosting.rest.api.controllers.Accomodation;

import static com.hosting.rest.api.Utils.PaginationConstants.DEFAULT_PAGE_NUMBER;
import static com.hosting.rest.api.Utils.PaginationConstants.DEFAULT_PAGE_SIZE;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
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

import com.hosting.rest.api.exceptions.IllegalArguments.IllegalArgumentsCustomException;
import com.hosting.rest.api.models.Accomodation.AccomodationModel;
import com.hosting.rest.api.services.Accomodation.AccomodationServiceImpl;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Francisco Coya Abajo
 * @version v1.0.1
 * @apiNote Controlador de alojamientos.
 */
@RestController
@CrossOrigin(origins = { "http://localhost:3000" })
@RequestMapping("/accomodations")
@Slf4j
public class AccomodationController {

	@Autowired
	private AccomodationServiceImpl accomodationService;

	@PreAuthorize("hasRole('ROLE_HOST_USER') or hasRole('ROLE_ADMIN_USER')")
	@PostMapping("new")
	public AccomodationModel addNewAccomodation(@RequestBody final AccomodationModel accomodationModel) {
		return accomodationService.addNewAccomodation(accomodationModel);
	}

	@PreAuthorize("hasRole('ROLE_BASE_USER') or hasRole('ROLE_HOST_USER') or hasRole('ROLE_ADMIN_USER')")
	@GetMapping("all")
	public Page<AccomodationModel> getAllAccomodationPaging(
			@RequestParam(value = "page", defaultValue = DEFAULT_PAGE_NUMBER) final String pageNumber,
			@RequestParam(value = "size", defaultValue = DEFAULT_PAGE_SIZE) final String size) {
		Page<AccomodationModel> accomodations = null;

		try {
			accomodations = accomodationService.findAllAccomodations(Integer.parseInt(pageNumber),
					Integer.parseInt(size));

		} catch (NumberFormatException nfe) {
			log.error("Alguno de los valores parámetros pasados para listar los alojamientos no es un número. ", nfe);
			throw new IllegalArgumentsCustomException(
					"Alguno de los valores parámetros pasados para listar los alojamientos no es un número. ", nfe);
		}

		return accomodations;
	}

	@PreAuthorize("hasRole('ROLE_BASE_USER') or hasRole('ROLE_HOST_USER') or hasRole('ROLE_ADMIN_USER')")
	@GetMapping("all/limit")
	public List<AccomodationModel> findAllAccomodations(@RequestParam(value = "max") final String maxResults) {
		List<AccomodationModel> accomodations = null;

		try {
			accomodations = accomodationService.findNAccomodations(Integer.parseInt(maxResults));

		} catch (NumberFormatException nfe) {
			log.error("El número máximo de resultados a mostrar no es válido");
			throw new IllegalArgumentsCustomException("El número máximo de resultados a mostrar no es válido");
		}
		return accomodations;
	}

	@PreAuthorize("hasRole('ROLE_BASE_USER') or hasRole('ROLE_HOST_USER') or hasRole('ROLE_ADMIN_USER')")
	@GetMapping("{regNumber}")
	public AccomodationModel getAccomodationById(@PathVariable(value = "regNumber") final String regNumber) {
		return accomodationService.getAccomodationById(regNumber.trim());
	}

	@PreAuthorize("hasRole('ROLE_BASE_USER') or hasRole('ROLE_HOST_USER') or hasRole('ROLE_ADMIN_USER')")
	@GetMapping("cities/{city}")
	public Page<AccomodationModel> getAccomodationsByCity(@PathVariable(value = "city") final String city,
			@RequestParam(value = "page") final String pageNumber, @RequestParam(value = "size") String size) {
		Page<AccomodationModel> accomodations = null;

		try {
			accomodations = accomodationService.findByCity(city.trim(), Integer.parseInt(size), Integer.parseInt(size));

		} catch (NumberFormatException nfe) {
			log.error("Alguno de los valores parámetros pasados para listar los alojamientos de la ciudad " + city
					+ " no es un número. ", nfe);
			throw new IllegalArgumentsCustomException(
					"Alguno de los valores parámetros pasados para listar los alojamientos de la ciudad " + city
							+ " no es un número. ",
					nfe);
		}

		return accomodations;
	}

	@PreAuthorize("hasRole('ROLE_BASE_USER') or hasRole('ROLE_HOST_USER') or hasRole('ROLE_ADMIN_USER')")
	@GetMapping("nearby")
	public List<AccomodationModel> findNearbyAccomodations(@RequestParam(name = "lat") final BigDecimal latitude,
			@RequestParam(name = "lng") final BigDecimal longitude,
			@RequestParam(name = "distance") final Double distance) {

		return accomodationService.findByNearby(latitude, longitude, distance);
	}

	@PreAuthorize("hasRole('ROLE_BASE_USER') or hasRole('ROLE_HOST_USER') or hasRole('ROLE_ADMIN_USER')")
	@GetMapping("category/{categoryName}")
	public List<AccomodationModel> findByCategory(@PathVariable(value = "categoryName") final String categoryToFind) {
		return accomodationService.findByCategory(categoryToFind);
	}

	@PreAuthorize("hasRole('ROLE_BASE_USER') or hasRole('ROLE_HOST_USER') or hasRole('ROLE_ADMIN_USER')")
	@GetMapping("price")
	public List<AccomodationModel> findByPriceRange(@RequestParam(name = "minPrice") final BigDecimal minPrice,
			@RequestParam(name = "maxPrice") final BigDecimal maxPrice) {
		return accomodationService.findByPriceRange(minPrice, maxPrice);
	}

	@PreAuthorize("hasRole('ROLE_HOST_USER') or hasRole('ROLE_ADMIN_USER')")
	@PatchMapping("{regNumber}")
	public AccomodationModel updateAccomodationById(@PathVariable(value = "regNumber") final String regNumber,
			@RequestBody final AccomodationModel accomodationToUpdate) {
		return accomodationService.updateAccomodationById(regNumber, accomodationToUpdate);
	}

	@PreAuthorize("hasRole('ROLE_HOST_USER') or hasRole('ROLE_ADMIN_USER')")
	@DeleteMapping("{regNumber}")
	public void removeAccomodationById(@PathVariable(value = "regNumber") final String regNumber) {
		accomodationService.removeAccomodationById(regNumber);
	}

}
