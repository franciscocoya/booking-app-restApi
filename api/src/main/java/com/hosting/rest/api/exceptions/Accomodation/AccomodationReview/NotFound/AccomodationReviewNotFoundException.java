package com.hosting.rest.api.exceptions.Accomodation.AccomodationReview.NotFound;

/**
 * @author Francisco Coya · https://github.com/FranciscoCoya
 * @version v1.0.0
 * @description Excepción que se lanza cuando no se encuentra una valoración de
 *              un alojamiento.
 **/
public class AccomodationReviewNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public AccomodationReviewNotFoundException() {
		super();
	}

	public AccomodationReviewNotFoundException(final String message) {
		super(message);
	}
}
