package com.hosting.rest.api.Utils;

/**
 * 
 * @author Francisco Coya
 * @version v1.0.0
 *
 */
public class EntityUtils {

	/**
	 * Comprueba que un id de tipo Integer se a válido.
	 * 
	 * @param idToCheck
	 * @return
	 */
	public static boolean isIntegerIdValid(Integer idToCheck) {
		return idToCheck != null && idToCheck > 0;
	}

	/**
	 * Comprueba que un id de tipo String no es blanco, ni vacío, ni null.
	 * 
	 * @param idToCheck
	 * @return
	 */
	public static boolean isStringIdNotBlank(String idToCheck) {
		return !idToCheck.isBlank() && idToCheck.length() > 0 && isStringNotNull(idToCheck);
	}

	/**
	 * 
	 * @param strToCheck
	 * @return true si <code>strToCheck</code> no es null y false en caso contrario.
	 */
	private static boolean isStringNotNull(String strToCheck) {
		return strToCheck != null;
	}
}