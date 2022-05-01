package com.hosting.rest.api.Utils;

import static com.hosting.rest.api.Utils.Constants.ACCESS_TOKEN_VALIDITY_SECONDS;
import static com.hosting.rest.api.Utils.Constants.AUTHORITIES_KEY;
import static com.hosting.rest.api.Utils.Constants.ISSUER_TOKEN;
import static com.hosting.rest.api.Utils.Constants.SIGNING_KEY;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TokenProvider {

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

		return Jwts.builder()
				.setSubject(authentication.getName())
				.claim(AUTHORITIES_KEY, authorities)
				.signWith(SignatureAlgorithm.HS256, SIGNING_KEY)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setIssuer(ISSUER_TOKEN)
				.setExpiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_VALIDITY_SECONDS * 1000))
				.compact();
	}

	/**
	 * Obtiene el token de autenticación
	 * 
	 * @param token
	 * @param userDetails
	 * @return
	 */
	public static UsernamePasswordAuthenticationToken getAuthentication(final String token,
			final UserDetails userDetails) {

		final JwtParser jwtParser = Jwts.parser().setSigningKey(SIGNING_KEY);

		final Jws<Claims> claimsJws = jwtParser.parseClaimsJws(token);

		final Claims claims = claimsJws.getBody();

		final Collection<SimpleGrantedAuthority> authorities = Arrays
				.stream(claims.get(AUTHORITIES_KEY).toString().split(",")).map(SimpleGrantedAuthority::new)
				.collect(Collectors.toList());

		return new UsernamePasswordAuthenticationToken(userDetails, "", authorities);
	}

	/**
	 * 
	 * @param token
	 * @return
	 */
	public static String getUserName(final String token) {
		final JwtParser jwtParser = Jwts.parser().setSigningKey(SIGNING_KEY);

		final Jws<Claims> claimsJws = jwtParser.parseClaimsJws(token);

		return claimsJws.getBody().getSubject();
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
			Jwts.parser().setSigningKey(authToken).parseClaimsJws(authToken);
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
