package com.hosting.rest.api.exceptions.Booking.BookingBill.IllegalArguments;

/**
 * @author Francisco Coya · https://github.com/FranciscoCoya
 * @version v1.0.0
 * @description Se muestra cuando se introduce algún parámetro incorrecto en un
 *              factura de reserva de alojamiento.
 **/
public class IllegalBookingBillArgumentsException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public IllegalBookingBillArgumentsException() {
		super();
	}

	public IllegalBookingBillArgumentsException(final String message) {
		super(message);
	}

}
