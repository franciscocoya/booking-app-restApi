package com.hosting.rest.api.Utils;

import com.hosting.rest.api.exceptions.IllegalArguments.IllegalArgumentsCustomException;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author Francisco Coya
 * @version v1.0.0
 * @apiNote Validaciones genéricas de parámetros.
 *
 * @param <T>
 * @param <R>
 */
@Slf4j
public class ServiceParamValidator {

	/**
	 * Valida que se cumpla la condición <code>condition</code>.
	 * 
	 * En caso de no cumplir la condicion, lanzará una excepción y mostrará un log
	 * con el mensaje pasado como parámetro en <code>exceptionAndLogMessage</code>.
	 * 
	 * @param serviceParamsToValidate
	 * @param condition
	 * @param exceptionAndLogMessage
	 */
	public static void validateParam(final Boolean condition, final String exceptionAndLogMessage) {
		if (!condition) {
			log.error(exceptionAndLogMessage);
			throw new IllegalArgumentsCustomException(exceptionAndLogMessage);
		}
	}
}
