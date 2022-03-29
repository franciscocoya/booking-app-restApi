package com.hosting.rest.api.controllers.Booking;

import java.util.List;

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
	public BookingModel addNewBooking(@RequestBody final BookingModel bookingToAdd) {
		return bookingService.addNewBooking(bookingToAdd);
	}

	@PutMapping("{bookingId}")
	public BookingModel updateBooking(@PathVariable(name = "bookingId") final Integer bookingId,
			@RequestBody final BookingModel bookingToAdd) {
		return bookingService.addNewBooking(bookingToAdd);
	}

	@DeleteMapping("{bookingId}")
	public void updateBooking(@PathVariable(name = "bookingId") final Integer bookingId) {
		bookingService.deleteBookingById(bookingId);
	}

	@GetMapping("{bookingId}")
	public BookingModel getBookingById(@PathVariable(name = "bookingId") final Integer bookingId) {
		return bookingService.getBookingById(bookingId);
	}

	@GetMapping("{bookingId}/{year}")
	public List<BookingModel> findByBookingYear(@PathVariable(name = "bookingId") final String regNumber, @PathVariable(name = "year") final Integer yearToSearch) {
		return bookingService.findByBookingYear(regNumber, yearToSearch);
	}

	// TODO: Listar todas las reservas de un usuario
	@GetMapping("users/{userId}")
	public List<BookingModel> listAllUserBookings(@PathVariable(name = "userId") final Integer userId) {
		return bookingService.findAllBookingByUser(userId);
	}

	// TODO: Histórico de reservas del alojamiento - ADMIN
}
