package com.hosting.rest.api.repositories.Booking.BookingBill;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hosting.rest.api.models.Booking.BookingBillModel;

public interface IBookingBillRepository extends JpaRepository<BookingBillModel, String> {

}
