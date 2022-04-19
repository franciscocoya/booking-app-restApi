package com.hosting.rest.api.controllers.Auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hosting.rest.api.models.Auth.AuthenticationRequest;
import com.hosting.rest.api.models.Auth.AuthenticationResponse;
import com.hosting.rest.api.services.User.UserServiceImpl;

@RestController
@RequestMapping("auth")
public class AuthController {

	private AuthenticationManager authenticationManager;

	@Autowired
	private UserServiceImpl userService;
	private JwtService jwtService;

	@PostMapping("/login")
	public AuthenticationResponse createToken(@RequestBody AuthenticationRequest authenticationRequest)
			throws Exception {
		try {
			UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
					authenticationRequest.getUsername(), authenticationRequest.getPassword());
			authenticationManager.authenticate(authentication);
		} catch (BadCredentialsException e) {
			throw new Exception("Invalid username or password", e);
		}
		
		UserDetails userDetails = userService.loadUserByUsername(authenticationRequest.getUsername());
		String token = jwtService.createToken(userDetails);
		return new AuthenticationResponse(token);
	}
}
