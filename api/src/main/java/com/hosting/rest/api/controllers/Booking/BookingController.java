package com.hosting.rest.api.controllers.Booking;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hosting.rest.api.exceptions.IllegalArguments.IllegalArgumentsCustomException;
import com.hosting.rest.api.models.Booking.BookingModel;
import com.hosting.rest.api.services.Booking.BookingServiceImpl;

import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author Francisco Coya
 * @version v1.0.3
 * @apiNote Controlador de las reservas de alojamientos.
 *
 */
@RestController
@RequestMapping("/bookings")
@Slf4j
public class BookingController {

	@Autowired
	private BookingServiceImpl bookingService;

	@PreAuthorize("hasRole('ROLE_HOST_USER') or hasRole('ROLE_ADMIN_USER')")
	@PostMapping("new")
	public BookingModel addNewBooking(@Valid @RequestBody final BookingModel bookingToAdd) {
		return bookingService.addNewBooking(bookingToAdd);
	}

	@PreAuthorize("hasRole('ROLE_HOST_USER') or hasRole('ROLE_ADMIN_USER')")
	@PatchMapping("{bookingId}")
	public BookingModel updateBooking(@PathVariable(name = "bookingId") final String bookingId,
			@RequestBody final BookingModel bookingToUpdate) {
		BookingModel bookingToReturn = null;

		try {
			bookingToReturn = bookingService.updateBookingDataById(Integer.parseInt(bookingId), bookingToUpdate);

		} catch (NumberFormatException nfe) {
			log.error("El id de la reserva no es un número.");
			throw new IllegalArgumentsCustomException("El id de la reserva no es un número.");
		}

		return bookingToReturn;
	}

	@PreAuthorize("hasRole('ROLE_HOST_USER') or hasRole('ROLE_ADMIN_USER')")
	@DeleteMapping("{bookingId}")
	public void deleteBooking(@PathVariable(name = "bookingId") final String bookingId) {
		try {
			bookingService.deleteBookingById(Integer.parseInt(bookingId));

		} catch (NumberFormatException nfe) {
			log.error("El id de reserva [ " + bookingId + " ] no es válido.");
			throw new IllegalArgumentsCustomException("El id de reserva [ " + bookingId + " ] no es válido.");
		}
	}

	@PreAuthorize("hasRole('ROLE_BASE_USER') or hasRole('ROLE_HOST_USER') or hasRole('ROLE_ADMIN_USER')")
	@GetMapping("{bookingId}")
	public BookingModel getBookingById(@PathVariable(name = "bookingId") final String bookingId) {
		BookingModel bookingToReturn = null;

		try {
			bookingToReturn = bookingService.getBookingById(Integer.parseInt(bookingId));

		} catch (NumberFormatException nfe) {
			log.error("El id de reserva [ " + bookingId + " ] no es válido.");
			throw new IllegalArgumentsCustomException("El id de reserva [ " + bookingId + " ] no es válido.");
		}
		return bookingToReturn;
	}

	@PreAuthorize("hasRole('ROLE_BASE_USER') or hasRole('ROLE_HOST_USER') or hasRole('ROLE_ADMIN_USER')")
	@GetMapping("{bookingId}/{year}")
	public List<BookingModel> findByBookingYear(@PathVariable(name = "bookingId") final String regNumber,
			@PathVariable(name = "year") final Integer yearToSearch) {
		return bookingService.findByBookingYear(regNumber, yearToSearch);
	}

	@PreAuthorize("hasRole('ROLE_BASE_USER') or hasRole('ROLE_HOST_USER') or hasRole('ROLE_ADMIN_USER')")
	@GetMapping("users/{userId}")
	public List<BookingModel> listAllUserBookings(@PathVariable(name = "userId") final String userId) {
		List<BookingModel> bookingsToReturn = null;

		try {
			bookingsToReturn = bookingService.findAllBookingByUser(Integer.parseInt(userId));

		} catch (NumberFormatException nfe) {
			log.error("El id de usuario [ " + userId + " ] no es válido.");
			throw new IllegalArgumentsCustomException("El id de usuario [ " + userId + " ] no es válido.");
		}
		return bookingsToReturn;
	}

	@PreAuthorize("hasRole('ROLE_BASE_USER') or hasRole('ROLE_HOST_USER') or hasRole('ROLE_ADMIN_USER')")
	@GetMapping("users/{userId}/count")
	public int countUserBookings(@PathVariable(name = "userId") final String userId) {
		int numOfBookings = 0;

		try {
			numOfBookings = bookingService.getNumOfBookingsByUserId(Integer.parseInt(userId));

		} catch (NumberFormatException nfe) {
			log.error("El id de usuario [ " + userId + " ] no es válido.");
			throw new IllegalArgumentsCustomException("El id de usuario [ " + userId + " ] no es válido.");
		}
		
		return numOfBookings;
	}
}
