package com.hosting.rest.api.controllers.Auth;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hosting.rest.api.configuration.security.JwtUtils;
import com.hosting.rest.api.exceptions.IllegalArguments.IllegalArgumentsCustomException;
import com.hosting.rest.api.models.Auth.JwtResponse;
import com.hosting.rest.api.models.Auth.LoginRequest;
import com.hosting.rest.api.models.Auth.ResetPasswordPayload;
import com.hosting.rest.api.models.Auth.SignUpRequest;
import com.hosting.rest.api.services.Auth.AuthServiceImpl;
import com.hosting.rest.api.services.UserDetails.UserDetailsImpl;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author Francisco Coya
 * @version v1.0.0
 * @apiNote Controlador para la autenticación y registro de usuarios de la
 *          aplicación.
 *
 */
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/auth")
@Slf4j
public class AuthController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private AuthServiceImpl authService;

	@PostMapping("signin")
	public ResponseEntity<?> signIn(@Valid @RequestBody final LoginRequest loginRequest) {

		// Autenticación del usuario
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		// Contexto de seguridad de Spring
		SecurityContextHolder.getContext()
				.setAuthentication((org.springframework.security.core.Authentication) authentication);

		// Generación del token
		String jwtToken = JwtUtils.generateToken(authentication);

		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

		List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
				.collect(Collectors.toList());

		return ResponseEntity.ok(new JwtResponse(jwtToken, userDetails.getId(), userDetails.getUsername(), roles));
	}

	@PostMapping("signup")
	public ResponseEntity<?> signUp(@Valid @RequestBody final SignUpRequest signUpPayload) {
		return authService.addNewUser(signUpPayload);
	}

	@PatchMapping("{userId}/password/reset")
	public ResponseEntity<?> resetPassword(@PathVariable(name = "userId") final String userId,
			@Valid @RequestBody final ResetPasswordPayload passwordPayload) {
		ResponseEntity<?> response = null;
		try {

			response = authService.resetPassword(Integer.parseInt(userId), passwordPayload);

		} catch (NumberFormatException nfe) {
			log.error("El id de usuario [ " + userId + " ] no es un número.");
			throw new IllegalArgumentsCustomException("El id de usuario [ " + userId + " ] no es un número.");
		}

		return response;
	}
}
