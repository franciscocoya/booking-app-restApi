package com.hosting.rest.api.exceptions.Booking.BookingBill.NotFound;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 
 * @author Francisco Coya
 * @version v1.0.0
 * @apiNote Manejador de la excepción BookingBillNotFoundException.
 *
 */
@ControllerAdvice
public class BookingNotFoundHandler {

	@ResponseBody
	@ExceptionHandler({ BookingBillNotFoundException.class })
	public ResponseEntity<Map<String, String>> bookingBillNotFoundHandler(final HttpServletRequest req,
			final BookingBillNotFoundException exception) {

		Map<String, String> errorResponse = new HashMap<>();

		errorResponse.put("path", req.getServletPath());
		errorResponse.put("status", HttpStatus.NOT_FOUND.toString());
		errorResponse.put("message", exception.getMessage());

		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
	}

}
