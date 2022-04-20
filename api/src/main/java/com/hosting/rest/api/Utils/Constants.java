package com.hosting.rest.api.Utils;

public class Constants {
	
	/**
	 * Ruta de la peticion de login para generar el token.
	 */
	public static final String REQUEST_TOKEN_URL = "/api/auth/signin";
	
	/**
	 * Rutas autorizadas sin token.
	 */
	public static final String GRANTED_AUTH_PATH = "/api/auth/**";

	/**
	 * 
	 */
	public static final String AUTHORITIES_KEY = "CLAIM_TOKEN";

	public static final String SIGNING_KEY = "KEY_1234";

	/**
	 * Tiempo de validación del token.
	 */
	public static final int ACCESS_TOKEN_VALIDITY_SECONDS = 28800;

	public static final String ISSUER_TOKEN = "ISSUER";

	/**
	 * Nombre de la cabecera HTTP a buscar el token a validar.
	 */
	public static final String HEADER_AUTHORIZATION_KEY = "Authorization";

	/**
	 *
	 */
	public static final String TOKEN_BEARER_PREFIX = "Bearer";
}
