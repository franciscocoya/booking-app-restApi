package com.hosting.rest.api.exceptions.Accomodation.AccomodationReview.AccomodationReviewReply.NotFound;

/**
 * @author Francisco Coya · https://github.com/FranciscoCoya
 * @version v1.0.0
 * @description Excepción que se lanza cuando no se encuentra una valoración de
 *              un alojamiento.
 **/
public class AccomodationReviewReplyNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public AccomodationReviewReplyNotFoundException() {
		super();
	}

	public AccomodationReviewReplyNotFoundException(final String message) {
		super(message);
	}
}
