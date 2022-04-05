package com.hosting.rest.api.controllers.User;

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
import com.hosting.rest.api.models.User.UserModel;
import com.hosting.rest.api.services.User.UserServiceImpl;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserServiceImpl userService;

	@PostMapping(value = "new")
	public UserModel addNewUser(@RequestBody final UserModel userToCreate) {
		return userService.addNewUser(userToCreate);
	}

	@DeleteMapping(value = "{userId}")
	public void deleteUserById(@PathVariable(value = "userId") final String userId) {
		try {
			userService.deleteUserById(Integer.parseInt(userId));
			
		} catch (NumberFormatException nfe) {
			throw new IllegalArgumentsCustomException("El id del usuario [ " + userId + " ] no es válido.");
		}
	}

	@PutMapping("{userId}")
	public UserModel udpateUser(@PathVariable(name = "userId") final Integer userId,
			@RequestBody UserModel userModelToUpdate) {
		return userService.updateUser(userId, userModelToUpdate);
	}

	@GetMapping("all/started")
	public List<UserModel> getAllStartedUsers() {
		return userService.findAllStartedUsers();
	}

	@GetMapping("all")
	public List<UserModel> getAllUsers() {
		return userService.findAllUsers();
	}

	@GetMapping("{userId}")
	public UserModel getUserById(@PathVariable(value = "userId") final String userId) {
		UserModel userToReturn = null;
		try {
			userToReturn = userService.getUserById(Integer.parseInt(userId));

		} catch (NumberFormatException nfe) {
			throw new IllegalArgumentsCustomException("El id del usuario ha de ser un valor numérico.");
		}

		return userToReturn;
	}
}
