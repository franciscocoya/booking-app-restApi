package com.hosting.rest.api.exceptions.Accomodation.IllegalArguments;

/**
 * @author Francisco Coya · https://github.com/FranciscoCoya
 * @version v1.0.0
 * @description Se muestra cuando se introduce mal el número de registro del
 *              alojamiento.
 **/
public class IllegalAccomodationArgumentsException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public IllegalAccomodationArgumentsException() {
		super();
	}

	public IllegalAccomodationArgumentsException(final String message) {
		super(message);
	}

	public IllegalAccomodationArgumentsException(final String message, final String param) {
		super(message + " [ " + param + " ] no es válido.");
	}

}
