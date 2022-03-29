package com.hosting.rest.api.exceptions.Plan.NotFound;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Francisco Coya · https://github.com/FranciscoCoya
 * @version v1.0.0
 * @description
 **/
@ControllerAdvice
public class PlanNotFoundHandler {

	@ResponseBody
	@ExceptionHandler(PlanNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String planNotFoundHandler(PlanNotFoundException e) {
		return e.getMessage();
	}
}
