package com.hosting.rest.api.exceptions.Plan.IllegalArgument;

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
public class IllegalPlanArgumentsHandler {

	@ResponseBody
	@ExceptionHandler(IllegalPlanArgumentsException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public String illegalPlanArguments(final IllegalPlanArgumentsException e) {
		return e.getMessage();
	}
}
