package com.hosting.rest.api.exceptions.Booking.BookingBill.NotFound;

/**
 * 
 * @author Francisco Coya
 * @version v1.0.0
 * @apiNote Excepción que se muestra cuando no es encuentra una factura de
 *          reserva de alojamiento.
 *
 */
public class BookingBillNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public BookingBillNotFoundException() {
		super();
	}

	public BookingBillNotFoundException(final String message) {
		super(message);
	}

}
