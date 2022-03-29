package com.hosting.rest.api.exceptions.User;

public class UserNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UserNotFoundException(Integer userId) {
		super("No se encontró ningún usuario con el id [" + userId + "]");
	}
}
