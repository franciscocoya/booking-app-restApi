package com.hosting.rest.api.services.Auth;

import org.springframework.http.ResponseEntity;

import com.hosting.rest.api.models.Auth.ResetPasswordPayload;
import com.hosting.rest.api.models.Auth.SignUpRequest;

public interface AuthService {
	
	public static final String CHANGE_PASSWORD_URI = "/auth/user/changePassword?token=";

	public ResponseEntity<?> addNewUser(final SignUpRequest signUpPayload);
	
	public ResponseEntity<?> resetPassword(final String emailToResetPassword);
	
	public void resetPasswordLoggedUser(final Integer userId, final ResetPasswordPayload resetPasswordPayload);
}
