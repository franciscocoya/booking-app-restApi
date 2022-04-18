package com.hosting.rest.api.controllers.Accomodation.AccomodationRule;

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

import com.hosting.rest.api.exceptions.IllegalArguments.IllegalArgumentsCustomException;
import com.hosting.rest.api.models.Accomodation.AccomodationRule.AccomodationRuleModel;
import com.hosting.rest.api.services.Accomodation.AccomodationRule.AccomodationRuleServiceImpl;

@RestController
@RequestMapping("/accomodations/rules")
public class AccomodationRuleController {

	@Autowired
	private AccomodationRuleServiceImpl accomodationRuleService;

	@PostMapping("new")
	public AccomodationRuleModel addNewAccomodationRule(@RequestBody final AccomodationRuleModel accomodationRuleToAdd) {
		return accomodationRuleService.addNewAccomodationRule(accomodationRuleToAdd);
	}

	@PutMapping("{accomodationRuleId}")
	public void updateAccomodationRule(@PathVariable(value = "accomodationRuleId") final String accomodationRuleId,
			@RequestBody final AccomodationRuleModel accomodationRuleToUpdate) {

		try {
			accomodationRuleService.updateAccomodationRule(Integer.parseInt(accomodationRuleId),
					accomodationRuleToUpdate);

		} catch (NumberFormatException nfe) {
			throw new IllegalArgumentsCustomException(
					"El id de la norma del alojamiento [ " + accomodationRuleId + " ] no es un número.");
		}

	}

	@DeleteMapping("{accomodationRuleId}")
	public void deleteAccomodationRuleById(
			@PathVariable(value = "accomodationRuleId") final String accomodationRuleId) {
		try {
			accomodationRuleService.deleteAccomodationRule(Integer.parseInt(accomodationRuleId));

		} catch (NumberFormatException nfe) {
			throw new IllegalArgumentsCustomException(
					"El id de la norma del alojamiento [ " + accomodationRuleId + " ] no es un número.");
		}
	}

	@GetMapping("{accomodationRuleId}")
	public AccomodationRuleModel findById(@PathVariable(value = "accomodationRuleId") final String accomodationRuleId) {
		AccomodationRuleModel accomodationRuleToReturn = null;

		try {
			accomodationRuleToReturn = accomodationRuleService.findById(Integer.parseInt(accomodationRuleId));

		} catch (NumberFormatException nfe) {
			throw new IllegalArgumentsCustomException(
					"El id de la norma del alojamiento [ " + accomodationRuleId + " ] no es un número.");
		}

		return accomodationRuleToReturn;
	}

	@GetMapping("{accomodationRegNumber}/all")
	public List<AccomodationRuleModel> findByAccomodationRegNumber(
			@PathVariable(value = "accomodationRegNumber") String regNumber) {
		return accomodationRuleService.findByAccomodationRegNumber(regNumber);
	}

}
