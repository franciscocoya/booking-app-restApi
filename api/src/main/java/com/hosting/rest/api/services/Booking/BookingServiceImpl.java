package com.hosting.rest.api.services.Booking;

import static com.hosting.rest.api.Utils.AppUtils.isIntegerValidAndPositive;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hosting.rest.api.exceptions.IllegalArguments.IllegalArgumentsCustomException;
import com.hosting.rest.api.exceptions.NotFound.NotFoundCustomException;
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
		String listBookingFromYearQuery = "SELECT bm " + "FROM BookingModel bm INNER JOIN bm.idAccomodation ac "
				+ "WHERE YEAR(bm.createdAt) = :year AND ac.registerNumber = :registerNumber";

		TypedQuery<BookingModel> bookings = em.createQuery(listBookingFromYearQuery, BookingModel.class);

		bookings.setParameter("year", yearToSearch);
		bookings.setParameter("registerNumber", regNumber);

		return bookings.getResultList();
	}

	@Override
	public BookingModel addNewBooking(final BookingModel bookingModelToCreate) {
		if (bookingModelToCreate == null) {
			throw new IllegalArgumentsCustomException("Los datos de la reserva a crear no son válidos.");
		}

		return bookingRepo.save(bookingModelToCreate);
	}

	@Override
	public BookingModel updateBookingDataById(final Integer bookingId, final BookingModel bookingToUpdate) {
		boolean existsBooking = bookingRepo.existsById(bookingId);

		if (!isIntegerValidAndPositive(bookingId)) {
			throw new IllegalArgumentsCustomException("El id de reserva [ " + bookingId + " ] no es válido.");
		}

		if (!existsBooking) {
			throw new NotFoundCustomException("La reserva a actualizar no existe.");
		}

		// TODO: Actualizar la reserva

		return null;
	}

	@Override
	public void deleteBookingById(final Integer bookingId) {
		boolean existsBooking = bookingRepo.existsById(bookingId);

		if (!isIntegerValidAndPositive(bookingId)) {
			throw new IllegalArgumentsCustomException("El id de reserva [ " + bookingId + " ] no es válido.");
		}

		if (!existsBooking) {
			throw new NotFoundCustomException("La reserva a eliminar no existe.");
		}

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
		String findAllBookingsOfUserQuery = "SELECT bm " + "FROM BookingModel bm INNER JOIN bm.idUser host "
				+ "WHERE host.id = :userId";

		TypedQuery<BookingModel> userBookings = em.createQuery(findAllBookingsOfUserQuery, BookingModel.class);

		userBookings.setParameter("userId", userId);

		return userBookings.getResultList();
	}

	@Override
	public BookingModel getBookingById(final Integer bookingId) {
		if (!isIntegerValidAndPositive(bookingId)) {
			throw new IllegalArgumentsCustomException("El id de reserva [ " + bookingId + " ] no es válido.");
		}

		return bookingRepo.findById(bookingId).get();
	}
}
