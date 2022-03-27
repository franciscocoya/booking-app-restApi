package com.hosting.rest.api.services.Booking;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

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

	@Override
	public List<BookingModel> listBookingBetweenTwoDates(LocalDateTime dateStartToSearch,
			LocalDateTime dateEndToSeach) {

		// TODO: REVISAR QUERY
//		String listBookingBetweenTwoDatesQuery = "SELECT * FROM BOOKING WHERE CHECK_IN > :dateFinish AND CHECK_OUT < :dateEnd";
//
//		TypedQuery<BookingModel> bookings = getEntityManager().createQuery(listBookingBetweenTwoDatesQuery,
//				BookingModel.class);
//
//		bookings.setParameter("dateStart", dateStartToSearch);
//		bookings.setParameter("dateFinish", dateEndToSeach);
//
//		return bookings.getResultList();
		return null;
	}

	@Override
	public List<BookingModel> listBookingFromYear(int yearToSearch) {
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
	public BookingModel addNewBooking(BookingModel bookingModelToCreate) {
		return bookingRepo.save(bookingModelToCreate);
	}

	@Override
	public BookingModel updateBookingDataById(Integer bookingId) {
		// TODO:
		return null;
	}

	@Override
	public void deleteBookingById(Integer bookingId) {
		bookingRepo.deleteById(bookingId);
	}

	@Override
	public int getNumOfBookingsByUserId(Integer userId) {
		// TODO: REVISAR QUERY
//		String getNumOfBookingsByUserIdQuery = "SELECT COUNT(*) FROM BookingModel bM INNER JOIN UserModel uM ON (bM.ID_USER = uM.ID) WHERE bM.ID_USER = :userId";
//
//		Query bookingsCount = getEntityManager().createQuery(getNumOfBookingsByUserIdQuery);
//		bookingsCount.setParameter("userId", userId);
//
//		return bookingsCount.getFirstResult();
		return -1;
	}

	@Override
	public List<BookingModel> listAllBookingByUser(Integer userId) {
		// TODO: REVISAR QUERY
//		String getAllBookingByUserQuery = "SELECT * FROM BookingModel bM INNER JOIN UserModel uM ON (bM.idHost = uM.ID) WHERE bM.ID_USER = :userId";
//
//		TypedQuery<BookingModel> bookings = getEntityManager().createQuery(getAllBookingByUserQuery,
//				BookingModel.class);
//
//		bookings.setParameter("userId", userId);
//
//		return bookings.getResultList();
		return null;
	}

//	private EntityManager getEntityManager() {
//		return entityManager;
//	}

}
