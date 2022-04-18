package com.hosting.rest.api.controllers.User;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.hosting.rest.api.models.User.UserHostModel;
import com.hosting.rest.api.services.User.UserHost.IUserHostServiceImpl;

@RestController
@RequestMapping("users/hosts")
public class UserHostController {

	@Autowired
	private IUserHostServiceImpl userHostService;

	@PostMapping("{userId}/upgrade")
	public UserHostModel upgradeExistingUserToUserHost(@PathVariable(value = "userId") final String userId,
			@RequestParam(name = "dni") final String userHostDni,
			@RequestParam(name = "direction") final String useHostDirection) {

		UserHostModel userHostAdded = null;

		try {
			userHostAdded = userHostService.upgradeUserToUserHost(Integer.parseInt(userId), userHostDni,
					useHostDirection);

		} catch (NumberFormatException nfe) {
			throw new IllegalArgumentsCustomException("El id de usuario [ " + userId + " ] a añadir no es un número.");
		}

		return userHostAdded;
	}

	@DeleteMapping("{userId}")
	public void downgradeUserHostToUser(@PathVariable(value = "userId") String userId) {
		try {
			userHostService.downgradeUserHostToUser(Integer.parseInt(userId));

		} catch (NumberFormatException nfe) {
			throw new IllegalArgumentsCustomException(
					"El id de usuario [ " + userId + " ] a eliminar no es un número.");
		}
	}

	@PatchMapping("{userId}")
	public UserHostModel updateUserHost(@PathVariable(value = "userId") final String userId,
			@Valid @RequestBody final UserHostModel userHostToUpdate) {
		UserHostModel updatedUserHost = null;
		
		try {
			updatedUserHost = userHostService.updateUserHostById(Integer.parseInt(userId), userHostToUpdate);

		} catch (NumberFormatException nfe) {
			throw new IllegalArgumentsCustomException(
					"El id de usuario [ " + userId + " ] a actualizar no es un número.");
		}
		
		return updatedUserHost;
	}

	@GetMapping("all")
	public List<UserHostModel> findAll() {
		return userHostService.findAll();
	}
}
