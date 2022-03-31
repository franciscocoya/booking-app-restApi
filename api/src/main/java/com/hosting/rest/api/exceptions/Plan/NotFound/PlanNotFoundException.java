package com.hosting.rest.api.exceptions.Plan.NotFound;

/**
 * @author Francisco Coya · https://github.com/FranciscoCoya
 * @version v1.0.0
 * @description Se muestra cuando no se encuentra un Plan de subscripción.
 **/
public class PlanNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public PlanNotFoundException() {
		super();
	}

	public PlanNotFoundException(final Integer planId) {
		super("No se encontró ningún plan con id: [" + planId + "]");
	}
}
