package com.hosting.rest.api.controllers.User.UserConfiguration;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hosting.rest.api.exceptions.IllegalArguments.IllegalArgumentsCustomException;
import com.hosting.rest.api.models.User.UserConfiguration.UserConfigurationModel;
import com.hosting.rest.api.services.User.UserConfiguration.UserConfigurationServiceImpl;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/config")
public class UserConfigurationController {

	@Autowired
	private UserConfigurationServiceImpl userConfigurationService;

	@PostMapping("new")
	public UserConfigurationModel addNewUserConfiguration(
			@Valid @RequestBody final UserConfigurationModel userConfigurationToAdd) {
		return userConfigurationService.addNewUserConfiguration(userConfigurationToAdd);
	}

	@PutMapping("{userId}")
	public UserConfigurationModel udpateUserConfiguration(@PathVariable(value = "userId") final String userId,
			@Valid @RequestBody final UserConfigurationModel userConfigurationToUpdate) {
		UserConfigurationModel userConfigurationToReturn = null;

		try {

			userConfigurationToReturn = userConfigurationService.updateUserConfiguration(Integer.parseInt(userId),
					userConfigurationToUpdate.getIdLanguage(), userConfigurationToUpdate.getIdCurrency());

		} catch (NumberFormatException nfe) {
			throw new IllegalArgumentsCustomException("El id del usuario [ " + userId + " ] no es un número.");
		}

		return userConfigurationToReturn;
	}

	@DeleteMapping("{userConfigId}")
	public void deleteUserConfiguration(@PathVariable(value = "userConfigId") final String userConfigurationId) {
		try {
			userConfigurationService.deleteUserConfiguration(Integer.parseInt(userConfigurationId));

		} catch (NumberFormatException nfe) {
			throw new IllegalArgumentsCustomException(
					"El id de la configuración del usuario [ " + userConfigurationId + " ] no es un número.");
		}
	}

	@GetMapping("u/{userId}")
	public UserConfigurationModel findByUserId(@PathVariable(name = "userId") final String userId) {
		UserConfigurationModel userConfigurationToReturn = null;

		try {
			userConfigurationToReturn = userConfigurationService.findByUserId(Integer.parseInt(userId));

		} catch (NumberFormatException nfe) {
			throw new IllegalArgumentsCustomException("El id del usuario [ " + userId + " ] no es un número.");
		}

		return userConfigurationToReturn;
	}

	@GetMapping("all")
	public List<UserConfigurationModel> findAll() {
		return userConfigurationService.findAll();
	}
}
