package com.hosting.rest.api.models.Auth;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JwtResponse implements Serializable {

	private static final long serialVersionUID = -86401941514723100L;

	private String token;
	private Integer id;
	private String email;
	private List<String> roles;

	private String type = "Bearer";

	public JwtResponse(final String accessToken) {
		this.token = accessToken;
	}

	public JwtResponse(String accessToken, Integer id, String email, List<String> roles) {
		this.token = accessToken;
		this.id = id;
		this.email = email;
		this.roles = roles;
	}
}
