package com.hosting.rest.api.services.Booking;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hosting.rest.api.models.Booking.BookingModel;
import com.hosting.rest.api.repositories.Booking.IBookingRepository;

@Service
public class BookingServiceImpl implements IBookingService {

	@Autowired
	private IBookingRepository bookingRepo;

	@PersistenceContext
	private EntityManager em;

	@Override
	public List<BookingModel> findByBookingYear(final String regNumber, final Integer yearToSearch) {
		/**
		 * Listado de reservas de un alojamiento realizadas en un año específico:
		 * <code>yearToSearch</code>
		 */
		String listBookingFromYearQuery = "select bm from BookingModel bm inner join bm.idAccomodation ac"
				+ " where YEAR(bm.createdAt) = :year" + " and ac.registerNumber = :registerNumber";

		TypedQuery<BookingModel> bookings = em.createQuery(listBookingFromYearQuery, BookingModel.class);

		bookings.setParameter("year", yearToSearch);
		bookings.setParameter("registerNumber", regNumber);

		return bookings.getResultList();
	}

	@Override
	public BookingModel addNewBooking(final BookingModel bookingModelToCreate) {
		return bookingRepo.save(bookingModelToCreate);
	}

	@Override
	public BookingModel updateBookingDataById(final Integer bookingId, final BookingModel bookingToUpdate) {
		// TODO:
		return null;
	}

	@Override
	public void deleteBookingById(final Integer bookingId) {
		bookingRepo.deleteById(bookingId);
	}

	@Override
	public int getNumOfBookingsByUserId(final Integer userId) {
//		return bookingRepo.findByHostUser(userId).size();
		return -1;
	}

	@Override
	public List<BookingModel> findAllBookingByUser(final Integer userId) {
		/**
		 * Listado de la reservas realizadas por un usuario.
		 */
		String findAllBookingsOfUserQuery = "select bm from BookingModel bm inner join bm.idUser host where host.id = :userId";

		TypedQuery<BookingModel> userBookings = em.createQuery(findAllBookingsOfUserQuery, BookingModel.class);

		userBookings.setParameter("userId", userId);

		return userBookings.getResultList();
	}

	@Override
	public BookingModel getBookingById(final Integer bookingId) {
		return bookingRepo.findById(bookingId).get();
	}
}
