package com.hosting.rest.api.exceptions.Accomodation.AccomodationReview.AccomodationReviewReply.IllegalArgument;

/**
 * @author Francisco Coya · https://github.com/FranciscoCoya
 * @version v1.0.0
 * @description Se muestra cuando se introduce mal el número de registro del
 *              alojamiento.
 **/
public class IllegalAccomodationReviewReplyArgumentsException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public IllegalAccomodationReviewReplyArgumentsException() {
		super();
	}

	public IllegalAccomodationReviewReplyArgumentsException(final String message) {
		super(message);
	}

	public IllegalAccomodationReviewReplyArgumentsException(final String message, final String param) {
		super(message + " [ " + param + " ] no es válido.");
	}

}
