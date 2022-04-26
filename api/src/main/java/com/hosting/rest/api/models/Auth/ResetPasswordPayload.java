package com.hosting.rest.api.models.Auth;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResetPasswordPayload implements Serializable{
	
	private static final long serialVersionUID = 6216686772842571283L;
	
	@NotBlank
	@JsonProperty("email")
	private String userEmail;
	
	@NotBlank
	private String oldPassword;
	
	@NotBlank
	private String newPassword;

	@NotBlank
	private String newPasswordRepeated;

}
