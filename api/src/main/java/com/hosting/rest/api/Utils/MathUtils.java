package com.hosting.rest.api.Utils;

import java.math.MathContext;
import java.math.RoundingMode;

/**
 * @author Francisco Coya
 * @version v1.0.0
 *
 */
public class MathUtils {

	private static final int DEFAULT_BIGDECIMAL_PRECISION = 32;
	private static final RoundingMode DEFAULT_ROUNDING_MODE = RoundingMode.HALF_UP;

	/**
	 * Contexto matemático a trabajar en la aplicación.
	 */
	public static final MathContext MATH_CONTEXT = new MathContext(DEFAULT_BIGDECIMAL_PRECISION, DEFAULT_ROUNDING_MODE);
}
