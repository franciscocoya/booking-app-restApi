package com.hosting.rest.api.exceptions.User.UserReview.NotFound;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class UserReviewNotFoundHandler {

	@ResponseBody
	@ExceptionHandler(UserReviewNotFoundException.class)
	public ResponseEntity<Map<String, String>> userReviewNotFoundHandler(HttpServletRequest req,
			UserReviewNotFoundException exception) {
		
		Map<String, String> errorResponse = new HashMap<>();

		errorResponse.put("path", req.getServletPath());
		errorResponse.put("status", HttpStatus.NOT_FOUND.toString());
		errorResponse.put("message", exception.getMessage());
		
		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
	}
}
