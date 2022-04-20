package com.hosting.rest.api.configuration.security;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hosting.rest.api.Utils.TokenProvider;
import com.hosting.rest.api.models.Auth.AuthorizationRequest;

import static com.hosting.rest.api.Utils.Constants.*;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * @author Francisco Coya
 * @version v1.0.0
 * @apiNote Filtro de Autenticación de JWT. 
 *
 */
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private AuthenticationManager authenticationManager;

	public JwtAuthenticationFilter(final AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

	/**
	 * 
	 * Realiza la autenticación de la petición <code>request</code> con los datos de username (Email del usuario) y password.
	 * 
	 * @param request
	 * @param response
	 * 
	 * @return
	 * 
	 * @throws AuthenticationException
	 */
	@Override
	public Authentication attemptAuthentication(final HttpServletRequest request, final HttpServletResponse response)
			throws AuthenticationException {
		try {
			AuthorizationRequest userCredentials = new ObjectMapper().readValue(request.getInputStream(),
					AuthorizationRequest.class);

			// OJO! userCredentials.getUsername() devuelve el email del usuario.
			return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					userCredentials.getUsername(), userCredentials.getPassword()));
			
		} catch (IOException e) {
			return null;
		}
	}

	/**
	 * Añade la cabecera <code>Authorization</code> a la petición con el token generado.
	 * 
	 * @param request
	 * @param response
	 * @param chain
	 * @param authResult
	 * 
	 * @throws IOException
	 * @throws ServletException
	 */
	@Override
	protected void successfulAuthentication(final HttpServletRequest request, final HttpServletResponse response, final FilterChain chain,
			final Authentication authResult) throws IOException, ServletException {

		String token = TokenProvider.generateToken(authResult);
		response.addHeader(HEADER_AUTHORIZATION_KEY, TOKEN_BEARER_PREFIX + " " + token);
	}

}
