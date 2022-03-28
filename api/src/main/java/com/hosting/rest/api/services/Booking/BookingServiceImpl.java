package com.hosting.rest.api.services.Booking;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hosting.rest.api.models.Booking.BookingModel;
import com.hosting.rest.api.repositories.Booking.IBookingRepository;

@Service
public class BookingServiceImpl implements IBookingService {

	@Autowired
	private IBookingRepository bookingRepo;

//	@PersistenceContext
//	private EntityManager entityManager;

//	@Override
//	public List<BookingModel> listBookingsGroupByMonth() {
//		return null;
//	}

	@Override
	public List<BookingModel> listBookingFromYear(final int yearToSearch) {
		// TODO: REVISAR QUERY
//		String listBookingFromYearQuery = "SELECT * FROM BOOKING WHERE YEAR(CREATED_DATE) = :year";
//
//		TypedQuery<BookingModel> bookings = getEntityManager().createQuery(listBookingFromYearQuery,
//				BookingModel.class);
//
//		bookings.setParameter("year", yearToSearch);
//
//		return bookings.getResultList();
		return null;
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
		return bookingRepo.findByHostUser(userId).size();
	}

	@Override
	public List<BookingModel> listAllBookingByUser(final Integer userId) {
		return bookingRepo.findByHostUser(userId);
	}

	@Override
	public BookingModel getBookingById(final Integer bookingId) {
		return bookingRepo.findById(bookingId).get();
	}

//	private EntityManager getEntityManager() {
//		return entityManager;
//	}

}
