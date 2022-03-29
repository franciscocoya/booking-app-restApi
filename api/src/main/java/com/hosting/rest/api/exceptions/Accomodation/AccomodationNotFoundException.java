package com.hosting.rest.api.exceptions.Accomodation;

/**
 * @author Francisco Coya · https://github.com/FranciscoCoya
 * @version v1.0.0
 * @description Excepción que se lanza cuando no se encuentra un alojamiento.
 **/
public class AccomodationNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public AccomodationNotFoundException() {
		super();
	}

	public AccomodationNotFoundException(String regNumber) {
		super("No se encontró ningún alojamiento con número de registro [" + regNumber + " ]");
	}
}
