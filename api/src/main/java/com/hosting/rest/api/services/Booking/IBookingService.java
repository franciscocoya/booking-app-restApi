package com.hosting.rest.api.services.Booking;

import java.util.List;

import com.hosting.rest.api.models.Booking.BookingModel;

public interface IBookingService {

	// -- Para el administrador

//	List<BookingModel> listBookingsGroupByMonth();
	
	// TODO: Listado de reservas de un año específico.
	List<BookingModel> listBookingFromYear(int yearToSearch);

	// --

	BookingModel addNewBooking(final BookingModel bookingModelToCreate);
	
	BookingModel updateBookingDataById(final Integer bookingId, final BookingModel bookingToUpdate);
	
	BookingModel getBookingById(final Integer bookingId);

	void deleteBookingById(final Integer bookingId);
	
	
	
	// TODO: Número de reservas realizadas por un usuario.
	int getNumOfBookingsByUserId(final Integer userId);
	
	// TODO: Listado de reservas realizadas por un usuario.
	List<BookingModel> findAllBookingByUser(final Integer userId);

}
