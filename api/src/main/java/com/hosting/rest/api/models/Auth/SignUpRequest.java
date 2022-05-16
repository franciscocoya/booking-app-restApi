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

	@NotBlank(message = "El nombre es obligatorio")
	private String name;

	@NotBlank(message = "Los apellidos son obligatorios")
	private String surname;

	@Email(message = "El correo electrónico no es válido")
	@NotBlank(message = "El correo electrónico es obligatorio")
	private String email;

	@NotBlank(message = "La contraseña es obligatoria")
	private String password;

	@NotBlank(message = "Es obligatorio que repetir la contraseña")
	private String repeatedPassword;

}
