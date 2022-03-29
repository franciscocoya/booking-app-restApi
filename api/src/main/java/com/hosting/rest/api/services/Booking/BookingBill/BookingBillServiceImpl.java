package com.hosting.rest.api.services.Booking.BookingBill;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hosting.rest.api.models.Booking.BookingBillModel;
import com.hosting.rest.api.repositories.Booking.BookingBill.IBookingBillRepository;

@Service
public class BookingBillServiceImpl implements IBookingBillService {

	@Autowired
	private EntityManager em;

	@Autowired
	private IBookingBillRepository bookingBillRepo;

	@Override
	public BookingBillModel addNewBookingBill(final BookingBillModel bookingBillToAdd) {
		return bookingBillRepo.save(bookingBillToAdd);
	}

	@Override
	public BookingBillModel updateBookingBill(final String billNumber, final BookingBillModel bookingBillToUpdate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteBookingBill(final String billNumber) {
		bookingBillRepo.deleteById(billNumber);
	}

	@Override
	public List<BookingBillModel> findAllBookingBillsByAccomodation(final String accomodationRegNumber) {
		/**
		 * Listado de facturas de las reservas de un alojamiento
		 * <code>accomodationRegNumber</code>.
		 */
		String findAllBookingBillsByAccRegisterNumber = "select bn"
				+ " from BookingModel bm inner join bm.idAccomodation ac inner join bm.billNumber bn"
				+ " where ac.registerNumber = :regNumber";

		TypedQuery<BookingBillModel> bookings = em.createQuery(findAllBookingBillsByAccRegisterNumber,
				BookingBillModel.class);

		bookings.setParameter("regNumber", accomodationRegNumber);

		return bookings.getResultList();
	}
}
