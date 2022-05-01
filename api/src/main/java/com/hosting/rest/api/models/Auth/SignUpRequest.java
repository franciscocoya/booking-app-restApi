package com.hosting.rest.api.models.Auth;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignUpRequest implements Serializable {

	private static final long serialVersionUID = -4880495450171944598L;

	@NotBlank
	private String name;

	@NotBlank
	private String surname;

	@Email
	private String email;

	@NotBlank
	private String password;

	@NotBlank
	private String repeatedPassword;

}
