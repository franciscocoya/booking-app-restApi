package com.hosting.rest.api.Utils;

public class Constants {

	/**
	 * Ruta base de la api.
	 */
	public static final String API_BASE_URL = "http://localhost:8085";
	
	/**
	 * Ruta de la peticion de login para generar el token.
	 */
	public static final String REQUEST_TOKEN_URL = "/api/auth/signin";
	
	/**
	 * Ruta de logout de la api.
	 */
	public static final String API_LOGOUT_URL  = "/api/auth/logout";

	/**
	 * Rutas autorizadas sin token.
	 */
	public static final String GRANTED_AUTH_PATH = "/auth/**";

	/**
	 * Ruta para el acceso al login de la API.
	 */
	public static final String LOGIN_PATH = "/api/login";
	
	// http://localhost:3000
	public static final String CORS_ALLOWED_ORIGINS_PATH = "*";

	public static final String AUTHORITIES_KEY = "CLAIM_TOKEN";

	public static final String SIGNING_KEY = "bookingSecretKey";

	/**
	 * Tiempo de validación del token.
	 */
	public static final int ACCESS_TOKEN_VALIDITY_SECONDS = 86400000;

	public static final String ISSUER_TOKEN = "ISSUER";

	/**
	 * Nombre de la cabecera HTTP a buscar el token a validar.
	 */
	public static final String HEADER_AUTHORIZATION_KEY = "Authorization";

	/**
	 *
	 */
	public static final String TOKEN_BEARER_PREFIX = "Bearer ";
}
