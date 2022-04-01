package com.hosting.rest.api.services.Booking;

import java.util.List;

import com.hosting.rest.api.models.Booking.BookingModel;

public interface IBookingService {

	// -- Para el administrador

//	List<BookingModel> listBookingsGroupByMonth();

	List<BookingModel> findByBookingYear(final String regNumber, final Integer yearToSearch);

	// --

	BookingModel addNewBooking(final BookingModel bookingModelToCreate);

	BookingModel updateBookingDataById(final Integer bookingId, final BookingModel bookingToUpdate);

	BookingModel getBookingById(final Integer bookingId);

	void deleteBookingById(final Integer bookingId);

	int getNumOfBookingsByUserId(final Integer userId);

	List<BookingModel> findAllBookingByUser(final Integer userId);

}
