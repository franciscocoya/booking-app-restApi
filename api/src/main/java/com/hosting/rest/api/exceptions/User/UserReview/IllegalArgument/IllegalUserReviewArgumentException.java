package com.hosting.rest.api.exceptions.User.UserReview.IllegalArgument;

/**
 * @author Francisco Coya · https://github.com/FranciscoCoya
 * @version v1.0.0
 * @description Se muestra cuando se introduce algún parámetro incorrecto para
 *              una valoracion de un usuario.
 **/
public class IllegalUserReviewArgumentException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public IllegalUserReviewArgumentException() {
		super();
	}

	public IllegalUserReviewArgumentException(String message) {
		super(message);
	}

}
