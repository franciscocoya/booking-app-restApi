package com.hosting.rest.api.exceptions.Accomodation.AccomodationReview.IllegalArgument;

/**
 * @author Francisco Coya · https://github.com/FranciscoCoya
 * @version v1.0.0
 * @description Se muestra cuando se introduce mal el número de registro del
 *              alojamiento.
 **/
public class IllegalAccomodationReviewArgumentsException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public IllegalAccomodationReviewArgumentsException() {
		super();
	}

	public IllegalAccomodationReviewArgumentsException(final String message) {
		super(message);
	}

	public IllegalAccomodationReviewArgumentsException(final String message, final String param) {
		super(message + " [ " + param + " ] no es válido.");
	}

}
