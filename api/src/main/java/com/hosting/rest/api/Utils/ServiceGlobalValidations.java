package com.hosting.rest.api.Utils;

import static com.hosting.rest.api.Utils.ServiceParamValidator.validateParam;

/**
 * 
 * @author Francisco Coya
 * @version v1.0.0
 * @apiNote Contiene todas las validaciones genéricas realizadas en los
 *          servicios de la API.
 *
 */
public class ServiceGlobalValidations {

	/**
	 * Comprueba que el tamaño de página <code>pageSizeToValidate</code> es válido.
	 * 
	 * @param pageSizeToValidate
	 */
	public static void checkPageSize(final Integer pageSizeToValidate) throws NumberFormatException {
		validateParam(pageSizeToValidate < 1, "El tamaño de página [ " + pageSizeToValidate + " ] no es válido.");
	}

	/**
	 * Comprueba que el número de página <code>pageNumberToValidate</code> es
	 * válido.
	 * 
	 * @param pageNumberToValidate
	 */
	public static void checkPageNumber(final Integer pageNumberToValidate) throws NumberFormatException {
		validateParam(pageNumberToValidate < 0, "El número de página [ " + pageNumberToValidate + " ] no es válido.");
	}

}
