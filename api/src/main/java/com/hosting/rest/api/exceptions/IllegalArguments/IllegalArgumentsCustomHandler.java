package com.hosting.rest.api.exceptions.IllegalArguments;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Francisco Coya · https://github.com/FranciscoCoya
 * @version v1.0.0
 * @description Manejador de la exception IllegalAccomodationArgumentsException
 **/
@ControllerAdvice
public class IllegalArgumentsCustomHandler {
	@ResponseBody
	@ExceptionHandler(IllegalArgumentsCustomException.class)
	public ResponseEntity<Map<String, String>> accomodationIllegalArgsHandler(HttpServletRequest req,
			IllegalArgumentsCustomException exception) {
		
		Map<String, String> errorResponse = new HashMap<>();

		errorResponse.put("path", req.getServletPath());
		errorResponse.put("status", HttpStatus.NOT_FOUND.toString());
		errorResponse.put("message", exception.getMessage());
		
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}
}
