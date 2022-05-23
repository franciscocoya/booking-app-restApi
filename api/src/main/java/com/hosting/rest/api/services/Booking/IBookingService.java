package com.hosting.rest.api.services.Booking;

import static com.hosting.rest.api.Utils.MathUtils.MATH_CONTEXT;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import com.hosting.rest.api.models.Booking.BookingModel;
import com.hosting.rest.api.models.Booking.BookingStatus;

public interface IBookingService {

	/**
	 * Comisión por defecto de la aplicación. Se aplica a la cantidad calculada.
	 */
	public static final BigDecimal DEFAULT_APP_SERVICE_FEE = new BigDecimal("0.10", MATH_CONTEXT);

	// -- Para el administrador

	// List<BookingModel> listBookingsGroupByMonth();

	List<BookingModel> findByBookingYear(final String regNumber, final Integer yearToSearch);

	// --

	public BookingModel addNewBooking(final BookingModel bookingToAdd);

	public BookingModel updateBookingDataById(final Integer bookingId, final BookingModel bookingToUpdate);
	
	public void updateBookingStatus(final Integer bookingId, final BookingStatus newBookingStatus);

	public BookingModel getBookingById(final Integer bookingId);

	public void deleteBookingById(final Integer bookingId);

	public int getNumOfBookingsByUserId(final Integer userId);

	public List<BookingModel> findAllBookingByUser(final Integer userId);

	public Set<List<LocalDateTime>> checkAccomodationAvailability(final String regNumber);
	
	public List<BookingModel> findAllBookingFromHostAccomodations(final Integer userId);
 
}
