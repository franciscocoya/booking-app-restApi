package com.hosting.rest.api.services.Auth;

import org.springframework.http.ResponseEntity;

import com.hosting.rest.api.models.Auth.ResetPasswordPayload;
import com.hosting.rest.api.models.Auth.SignUpRequest;

public interface AuthService {

	public ResponseEntity<?> addNewUser(final SignUpRequest signUpPayload);
	
	public ResponseEntity<?> resetPassword(final Integer userId, final ResetPasswordPayload passwordPayload);
}
