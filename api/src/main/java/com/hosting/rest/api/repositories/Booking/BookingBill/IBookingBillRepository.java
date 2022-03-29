package com.hosting.rest.api.repositories.Booking.BookingBill;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hosting.rest.api.models.Booking.BookingBillModel;

@Repository
public interface IBookingBillRepository extends JpaRepository<BookingBillModel, String> {

}
