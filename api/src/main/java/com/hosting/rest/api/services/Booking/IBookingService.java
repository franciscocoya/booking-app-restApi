package com.hosting.rest.api.services.Booking;

import java.util.List;

import com.hosting.rest.api.models.Booking.BookingModel;

public interface IBookingService {

	// -- Para el administrador

//	List<BookingModel> listBookingsGroupByMonth();
	
	// TODO: Listado de reservas de un año específico.
	List<BookingModel> listBookingFromYear(int yearToSearch);

	// --

	BookingModel addNewBooking(BookingModel bookingModelToCreate);
	
	BookingModel updateBookingDataById(Integer bookingId, BookingModel bookingToUpdate);
	
	BookingModel getBookingById(Integer bookingId);

	void deleteBookingById(Integer bookingId);
	
	
	
	// TODO: Número de reservas realizadas por un usuario.
	int getNumOfBookingsByUserId(Integer userId);
	
	// TODO: Listado de reservas realizadas por un usuario.
	List<BookingModel> listAllBookingByUser(Integer userId);

}
