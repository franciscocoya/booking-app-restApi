package com.hosting.rest.api.exceptions.IllegalArguments;

/**
 * @author Francisco Coya · https://github.com/FranciscoCoya
 * @version v1.0.0
 * @description Se muestra cuando se introduce mal el número de registro del
 *              alojamiento.
 **/
public class IllegalArgumentsCustomException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public IllegalArgumentsCustomException() {
		super();
	}

	public IllegalArgumentsCustomException(final String message) {
		super(message);
	}

	public IllegalArgumentsCustomException(final String message, final String param) {
		super(message + " [ " + param + " ] no es válido.");
	}
	
	public IllegalArgumentsCustomException(final String message, final Exception exception) {
		super(message, exception);
	}

}
