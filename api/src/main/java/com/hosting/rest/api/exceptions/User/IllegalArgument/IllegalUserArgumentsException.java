package com.hosting.rest.api.exceptions.User.IllegalArgument;

/**
 * @author Francisco Coya · https://github.com/FranciscoCoya
 * @version v1.0.0
 * @description Se muestra cuando se introduce algún parámetro incorrecto para
 *              un usuario.
 **/
public class IllegalUserArgumentsException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public IllegalUserArgumentsException() {
		super();
	}

	public IllegalUserArgumentsException(String message) {
		super(message);
	}

}
