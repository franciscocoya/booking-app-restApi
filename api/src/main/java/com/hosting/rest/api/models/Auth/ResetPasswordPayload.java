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

	private String oldPassword;

	private String newPassword;

	private String newPasswordRepeated;

}
