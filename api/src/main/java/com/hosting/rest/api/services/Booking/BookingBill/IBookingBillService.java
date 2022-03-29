package com.hosting.rest.api.services.Booking.BookingBill;

import java.util.List;

import com.hosting.rest.api.models.Booking.BookingBillModel;

public interface IBookingBillService {

	public BookingBillModel addNewBookingBill(final BookingBillModel bookingBillToAdd);

	public BookingBillModel updateBookingBill(final String billNumber, final BookingBillModel bookingBillToUpdate);

	public void deleteBookingBill(final String billNumber);

	public List<BookingBillModel> findAllBookingBillsByAccomodation(final String accomodationRegNumber);
}
