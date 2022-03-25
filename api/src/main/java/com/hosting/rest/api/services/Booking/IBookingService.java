package com.hosting.rest.api.services.Booking;

import java.time.LocalDateTime;
import java.util.List;

import com.hosting.rest.api.models.Booking.BookingModel;

public interface IBookingService {

	// -- Para el administrador

	// TODO: Listado de reservas en un periodo de tiempo.
	List<BookingModel> listBookingBetweenTwoDates(LocalDateTime dateStartToSearch, LocalDateTime dateEndToSeach);
	
	// TODO: Listado de reservas de un año específico.
	List<BookingModel> listBookingFromYear(int yearToSearch);

	// --

	// TODO: Añadir una nueva reserva.
	BookingModel addNewBooking(BookingModel bookingModelToCreate);
	
	// TODO: Modificar los datos de una reserva existente.
	BookingModel updateBookingDataById(Integer bookingId);
	
	// TODO: Eliminar una reserva.
	void deleteBookingById(Integer bookingId);
	
	// TODO: Número de reservas realizadas por un usuario.
	int getNumOfBookingsByUserId(Integer userId);
	
	// TODO: Listado de reservas realizadas por un usuario.
	List<BookingModel> listAllBookingByUser(Integer userId);

}
