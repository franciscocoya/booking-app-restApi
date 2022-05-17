package com.hosting.rest.api.controllers.Auth;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
@CrossOrigin(origins = "*")
@RequestMapping("/auth")
@Slf4j
public class AuthController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private AuthServiceImpl authService;

	@Autowired
	private JwtUtils jwtUtils;

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

	@PostMapping("password/reset")
	public ResponseEntity<?> resetPassword(@RequestParam(name = "email") final String emailToFind) {
		return authService.resetPassword(emailToFind);
	}

	@PreAuthorize("hasRole('ROLE_BASE_USER') or hasRole('ROLE_HOST_USER') or hasRole('ROLE_ADMIN_USER')")
	@PutMapping("{userId}/password/reset")
	public void resetPasswordLoggedUser(@PathVariable(name = "userId") final String userId,
			@Valid @RequestBody final ResetPasswordPayload resetPasswordPayload) {

		try {
			authService.resetPasswordLoggedUser(Integer.parseInt(userId), resetPasswordPayload);

		} catch (NumberFormatException nfe) {
			log.error("El id de usuario no es un número.");
			throw new IllegalArgumentsCustomException("El id de usuario no es un número.");
		}

	}

	@PostMapping("user/changePassword")
	public ResponseEntity<?> changePassword(@RequestParam("token") final String resetToken) {
		// TODO:
		jwtUtils.validateJwtToken(resetToken);
		return null;
	}
}
