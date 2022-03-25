package com.hosting.rest.api.services.Booking.BookingBill;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hosting.rest.api.repositories.Booking.BookingBill.IBookingBillRepository;

@Service
public class BookingBillServiceImpl implements IBookingBillService {

	@Autowired
	private IBookingBillRepository bookingBillRepo;
}
