package com.hosting.rest.api.configuration.security;

import static com.hosting.rest.api.Utils.Constants.HEADER_AUTHORIZATION_KEY;
import static com.hosting.rest.api.Utils.Constants.TOKEN_BEARER_PREFIX;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.hosting.rest.api.Utils.TokenProvider;
import com.hosting.rest.api.services.User.UserServiceImpl;

/**
 * 
 * @author Francisco Coya
 * @version v1.0.0
 * @apiNote Filtro de autorización de JWT para filtrar las peticiones de la
 *          rutas que necesitan autorización.
 *
 */
public class JwtAuthorizationFilter extends OncePerRequestFilter {

	@Autowired
	private UserServiceImpl userService;

	/**
	 * 
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @param filterChain
	 * 
	 * @return
	 * 
	 * @throws ServletException
	 * @throws IOException
	 */
	@Override
	protected void doFilterInternal(final HttpServletRequest httpServletRequest,
			final HttpServletResponse httpServletResponse, final FilterChain filterChain)
			throws ServletException, IOException {

		// Obtener la cabecera 'Authorization' de la request.
		String authorizationHeader = httpServletRequest.getHeader(HEADER_AUTHORIZATION_KEY);

		// Verificar que el token está bien formado.
		if (!StringUtils.hasText(authorizationHeader) || !authorizationHeader.startsWith(TOKEN_BEARER_PREFIX)) {
			filterChain.doFilter(httpServletRequest, httpServletResponse);
			return;
		}

		// Reemplazar espacios en blanco del token.
		final String token = authorizationHeader.replace(TOKEN_BEARER_PREFIX + " ", "");

		// Obtener el email (Username de JWT) asociado a dicho token.
		String userEmail = TokenProvider.getUserName(token);

		// Cargar los datos del usuario con el email anterior.
		UserDetails user = userService.loadUserByUsername(userEmail);

		// Obtener la autenticación.
		UsernamePasswordAuthenticationToken authenticationToken = TokenProvider.getAuthentication(token, user);

		// Establecer el contexto de autenticación de Spring Security.
		SecurityContextHolder.getContext().setAuthentication(authenticationToken);

		// Aplicar el filtro a la petición de entrada.
		filterChain.doFilter(httpServletRequest, httpServletResponse);
	}
}
