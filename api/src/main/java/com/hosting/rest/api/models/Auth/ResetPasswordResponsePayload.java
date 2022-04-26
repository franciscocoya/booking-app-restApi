package com.hosting.rest.api.models.Auth;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class ResetPasswordResponsePayload implements Serializable{
	
	private static final long serialVersionUID = -8186913562646635555L;
	
	public static final int EXPIRATION_TIME = 60 * 24;
	
	private String resetToken;
	
	private Date expiryDate;
}
