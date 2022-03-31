package com.hosting.rest.api.controllers.Booking.BookingBill;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hosting.rest.api.models.Booking.BookingBillModel;
import com.hosting.rest.api.services.Booking.BookingBill.BookingBillServiceImpl;

@RestController
@RequestMapping(value = "bills")
public class BookingBillController {

	@Autowired
	private BookingBillServiceImpl bookingBillService;

	@PostMapping("new")
	public BookingBillModel addNewBookingBill(@RequestBody BookingBillModel bookingBillModel) {
		return bookingBillService.addNewBookingBill(bookingBillModel);
	}

	@DeleteMapping("{bookingBillId}")
	public void removeBookingBillById(@PathVariable(name = "bookingBillId") final String bookingBillId) {
		bookingBillService.deleteBookingBill(bookingBillId);
	}

	@GetMapping("booking/{accomodationRegNumber}")
	public List<BookingBillModel> listBookingBillHistory(
			@PathVariable(name = "accomodationRegNumber") final String accomodationRegNumber) {
		return bookingBillService.findAllBookingBillsByAccomodation(accomodationRegNumber);
	}
}
