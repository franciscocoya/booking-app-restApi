package com.hosting.rest.api.models.Auth;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationResponse implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String token;
}
