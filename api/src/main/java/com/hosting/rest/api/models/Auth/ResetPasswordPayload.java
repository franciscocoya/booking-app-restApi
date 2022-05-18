package com.hosting.rest.api.models.Auth;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResetPasswordPayload implements Serializable {

	private static final long serialVersionUID = 6216686772842571283L;

	@NotBlank(message = "La contraseña actual es obligatoria")
	private String oldPassword;

	@NotBlank(message = "La nueva contraseña es obligatoria")
	private String newPassword;

	@NotBlank(message = "Es obligatorio repetir la nueva contraseña")
	private String newPasswordRepeated;

}
