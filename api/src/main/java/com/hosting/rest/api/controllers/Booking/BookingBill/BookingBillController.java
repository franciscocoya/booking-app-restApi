package com.hosting.rest.api.controllers.Booking.BookingBill;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hosting.rest.api.models.Booking.BookingBillModel;
import com.hosting.rest.api.services.Booking.BookingBill.BookingBillServiceImpl;

@RestController
@RequestMapping(value = "/bills")
public class BookingBillController {
	
	@Autowired
	private BookingBillServiceImpl bookingBillService;
	
	// TODO: Crear una nueva factura
	@PostMapping(name = "/new")
	public BookingBillModel addNewBookingBill(BookingBillModel bookingBillModel) {
		// TODO;
		return null;
	}
	
	// TODO: Eliminar una factura
	@DeleteMapping(name = "/delete/{bookingBillId}")
	public void removeBookingBillById(@PathVariable(name = "bookingBillId") String bookingBillId) {
		// TODO;
	}
	
	// TODO: Listar todas las facturas de una reserva - histórico
	@GetMapping(name = "/booking/{bookingId}")
	public List<BookingBillModel> listBookingBillHistory(@PathVariable(name = "bookingId") String bookingId) {
		// TODO;
		return null;
	}
}
