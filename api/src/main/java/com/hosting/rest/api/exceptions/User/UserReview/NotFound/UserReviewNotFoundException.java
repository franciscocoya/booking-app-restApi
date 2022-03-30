package com.hosting.rest.api.exceptions.User.UserReview.NotFound;

/**
 * @author Francisco Coya · https://github.com/FranciscoCoya
 * @version v1.0.0
 * @description Excepción que se lanza cuando no se encuentra una valoración del
 *              usuario.
 **/
public class UserReviewNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UserReviewNotFoundException() {
		super();
	}

	public UserReviewNotFoundException(String message) {
		super(message);
	}

}
