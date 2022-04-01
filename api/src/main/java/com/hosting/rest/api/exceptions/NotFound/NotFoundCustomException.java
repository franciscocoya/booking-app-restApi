package com.hosting.rest.api.exceptions.NotFound;

/**
 * @author Francisco Coya · https://github.com/FranciscoCoya
 * @version v1.0.0
 * @description Excepción que se lanza cuando no se encuentra un alojamiento.
 **/
public class NotFoundCustomException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public NotFoundCustomException() {
		super();
	}

	public NotFoundCustomException(String message) {
		super(message);
	}
}
