package com.hosting.rest.api.Utils;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author Francisco Coya
 * @version v1.0.0
 *
 */
public class AppUtils {

//	private static final String VALID_LATITUDE_REGEX = "^(\\+|-)?(?:90(?:(?:\\.0{1,6})?)|(?:[0-9]|[1-8][0-9])(?:(?:\\.[0-9]{1,6})?))$";
//	private static final String VALID_LONGITUDE_REGEX = "^(\\+|-)?(?:180(?:(?:\\.0{1,6})?)|(?:[0-9]|[1-9][0-9]|1[0-7][0-9])(?:(?:\\.[0-9]{1,6})?))$";

	/**
	 * Comprueba que un id de tipo Integer se a válido.
	 * 
	 * @param idToCheck
	 * @return
	 */
	public static boolean isIntegerValidAndPositive(final Integer idToCheck) {
		return isNotNull(idToCheck) && idToCheck > 0;
	}

	/**
	 * Comprueba que un valor de tipo double sea válido y positivo.
	 * 
	 * @param doubleValueToCheck
	 * @return
	 */
	public static boolean isDoubleValidAndPositive(final Double doubleValueToCheck) {
		return isNotNull(doubleValueToCheck) && doubleValueToCheck > 0;
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
	 * Comprueba que un valor de tipo bigDecimal <code>bigDecimalToCheck</code> es
	 * válido.
	 * 
	 * @param bigDecimalToCheck
	 * @return
	 */
	public static boolean isBigDecimalValid(final BigDecimal bigDecimalToCheck) {
		return isNotNull(bigDecimalToCheck);
	}

	/**
	 * {@link #isBigDecimalValid(BigDecimal)}
	 * 
	 * Comprueba además que el valor sea positivo o no, en fución de
	 * <code>mustBePositive</code>
	 * 
	 * @param bigDecimalToCheck
	 * @param mustBePositive
	 * @return
	 */
	public static boolean isBigDecimalValid(final BigDecimal bigDecimalToCheck, boolean mustBePositive) {
		boolean isValid = mustBePositive ? bigDecimalToCheck.signum() == 1 : false;
		return isValid && isNotNull(bigDecimalToCheck);
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
	 * @param isLatitude           true si se desea validar la latitud y false para
	 *                             validar la longitud.
	 * @return
	 */
	public static boolean isValidGeographicCoordinate(final BigDecimal coordinateToValidate, final boolean isLatitude) {
		return coordinateToValidate != null;
//				&& coordinateToValidate.toString().matches(isLatitude ? VALID_LATITUDE_REGEX : VALID_LONGITUDE_REGEX);
	}

	/**
	 * Comprueba que, dadas dos fechas <code>firstDateToCompare</code> y
	 * <code>secondDateToCompare</code>, la primera fecha sea inferior a la segunda.
	 * 
	 * @param firstDateToCompare
	 * @param secondDateToCompare
	 * 
	 * @return Devuelve true si la primera fecha es inferior a la segunda y false en
	 *         caso contrario.
	 */
	public static boolean isFirstDateLessThanSecondDate(final LocalDateTime firstDateToCompare,
			final LocalDateTime secondDateToCompare) {
		return firstDateToCompare.isBefore(secondDateToCompare);
	}

	/**
	 * Comprueba que una fecha pasada como parámetro es válida. Para ello comprueba
	 * que sea distinta de null.
	 * 
	 * @param dateToCheck
	 * @return
	 */
	public static boolean isDateValid(final LocalDateTime dateToCheck) {
		return isNotNull(dateToCheck);
	}
}
