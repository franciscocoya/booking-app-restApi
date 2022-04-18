package com.hosting.rest.api.repositories.Booking;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hosting.rest.api.models.Booking.BookingModel;

@Repository
public interface IBookingRepository extends JpaRepository<BookingModel, Integer> {
}
