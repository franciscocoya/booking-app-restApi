package com.hosting.rest.api.models.Auth;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthorizationRequest implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("email")
	private String username;
	
	private String password;
}
