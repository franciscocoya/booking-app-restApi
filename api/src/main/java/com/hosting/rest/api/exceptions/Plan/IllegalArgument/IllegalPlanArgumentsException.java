package com.hosting.rest.api.exceptions.Plan.IllegalArgument;

/**
 * @author Francisco Coya · https://github.com/FranciscoCoya
 * @version v1.0.0
 * @description Se muestra cuando el id del plan no es correcto.
 **/
public class IllegalPlanArgumentsException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public IllegalPlanArgumentsException() {
		super();
	}

	public IllegalPlanArgumentsException(final Integer planId) {
		super("El id de plan [" + planId + "] ha de ser un valor numérico.");

	}
}
