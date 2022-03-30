package com.hosting.rest.api.Utils;

import java.math.BigDecimal;

/**
 * 
 * @author Francisco Coya
 * @version v1.0.0
 *
 */
public class AppUtils {

	private static final String VALID_COORDINATE_REGEX = "-?[1-9][0-9]*(\\.[0-9]+)?,\\s*-?[1-9][0-9]*(\\.[0-9]+)?";

	/**
	 * Comprueba que un id de tipo Integer se a válido.
	 * 
	 * @param idToCheck
	 * @return
	 */
	public static boolean isIntegerValidAndPositive(final Integer idToCheck) {
		return idToCheck != null && idToCheck > 0;
	}
	
	public static boolean isDoubleValidAndPositive(final Double doubleValueToCheck) {
		return doubleValueToCheck != null && doubleValueToCheck > 0;
	}

	/**
	 * Comprueba que un id de tipo String no es blanco, ni vacío, ni null.
	 * 
	 * @param idToCheck
	 * @return
	 */
	public static boolean isStringNotBlank(final String idToCheck) {
		return !idToCheck.isBlank() && idToCheck.length() > 0 && isNotNull(idToCheck);
	}

	/**
	 * 
	 * @param strToCheck
	 * @return true si <code>strToCheck</code> no es null y false en caso contrario.
	 */
	public static boolean isNotNull(final Object strToCheck) {
		return strToCheck != null;
	}

	/**
	 * Comprueba que una coordenada <code>coordinateToValidate</code> no sea null y
	 * esté bien formada.
	 * 
	 * @param coordinateToValidate
	 * @return
	 */
	public static boolean isValidGeographicCoordinate(final BigDecimal coordinateToValidate) {
		//&& String.valueOf(coordinateToValidate).matches(VALID_COORDINATE_REGEX)
		return coordinateToValidate != null ;
	}
}
