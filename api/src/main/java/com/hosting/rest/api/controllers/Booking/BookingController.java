package com.hosting.rest.api.controllers.Booking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hosting.rest.api.models.Booking.BookingModel;
import com.hosting.rest.api.services.Booking.BookingServiceImpl;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("bookings")
public class BookingController {
	
	@Autowired
	private BookingServiceImpl bookingService;
	
	@PostMapping("new")
	public BookingModel addNewBooking(@RequestBody BookingModel bookingToAdd) {
		return bookingService.addNewBooking(bookingToAdd);
	}
	
	@PutMapping("{bookingId}")
	public BookingModel updateBooking(@PathVariable Integer bookingId, @RequestBody BookingModel bookingToAdd) {
		return bookingService.addNewBooking(bookingToAdd);
	}
	
	@DeleteMapping("{bookingId}")
	public void updateBooking(@PathVariable Integer bookingId) {
		bookingService.deleteBookingById(bookingId);
	}
	
	@GetMapping("{bookingId}")
	public BookingModel getBookingById(@PathVariable Integer bookingId) {
		return bookingService.getBookingById(bookingId);
	}
	
	// TODO: Listar todas las reservas de un usuario
	
	
	// TODO: Histórico de reservas del alojamiento - ADMIN
}
