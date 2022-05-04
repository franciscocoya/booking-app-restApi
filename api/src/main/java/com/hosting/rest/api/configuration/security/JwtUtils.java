package com.hosting.rest.api.configuration.security;

import static com.hosting.rest.api.Utils.Constants.ACCESS_TOKEN_VALIDITY_SECONDS;
import static com.hosting.rest.api.Utils.Constants.AUTHORITIES_KEY;
import static com.hosting.rest.api.Utils.Constants.ISSUER_TOKEN;
import static com.hosting.rest.api.Utils.Constants.SIGNING_KEY;

import java.util.Date;
import java.util.stream.Collectors;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author Francisco Coya
 * @version v1.0.0
 * @apiNote Filtro de Autenticación de JWT.
 *
 */
@Slf4j
@Component
public class JwtUtils {

	/**
	 * Genera el token y le establece una fecha de expiración.
	 * 
	 * @param authentication
	 * 
	 * @return
	 */
	public static String generateToken(final Authentication authentication) {
		final String authorities = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority)
				.collect(Collectors.joining(","));

		return Jwts.builder().setSubject(authentication.getName())
				.claim(AUTHORITIES_KEY, authorities)
				.signWith(SignatureAlgorithm.HS512, SIGNING_KEY)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setIssuer(ISSUER_TOKEN)
				.setExpiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_VALIDITY_SECONDS * 1000))
				.compact();
	}
	
	/**
	 * 
	 * @param token
	 * @return
	 */
	public static String getUserNameFromJwtToken(final String token) {
		return Jwts.parser().setSigningKey(SIGNING_KEY).parseClaimsJws(token).getBody().getSubject();
	}

	/**
	 * Valida el token JWT <code>authToken</code> pasado como parámetro.
	 * 
	 * @param authToken
	 * 
	 * @return
	 */
	public boolean validateJwtToken(final String authToken) {
		try {
			Jwts.parser().setSigningKey(SIGNING_KEY).parseClaimsJws(authToken);
			return true;

		} catch (SignatureException e) {
			log.error("Invalid JWT signature: {}", e.getMessage());

		} catch (MalformedJwtException e) {
			log.error("Invalid JWT token: {}", e.getMessage());

		} catch (ExpiredJwtException e) {
			log.error("JWT token is expired: {}", e.getMessage());

		} catch (UnsupportedJwtException e) {
			log.error("JWT token is unsupported: {}", e.getMessage());

		} catch (IllegalArgumentException e) {
			log.error("JWT claims string is empty: {}", e.getMessage());
		}

		return false;
	}
}
