package com.hosting.rest.api.services.Booking.BookingBill;

import static com.hosting.rest.api.Utils.AppUtils.isNotNull;
import static com.hosting.rest.api.Utils.AppUtils.isStringNotBlank;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hosting.rest.api.exceptions.Accomodation.IllegalArguments.IllegalAccomodationArgumentsException;
import com.hosting.rest.api.exceptions.Booking.BookingBill.IllegalArguments.IllegalBookingBillArgumentsException;
import com.hosting.rest.api.exceptions.Booking.BookingBill.NotFound.BookingBillNotFoundException;
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

		if (!isNotNull(bookingBillToAdd)) {
			throw new IllegalBookingBillArgumentsException(
					"ALguno de los datos para crear la factura de reserva no es válido.");
		}

		boolean existsBookingBill = bookingBillRepo.existsById(bookingBillToAdd.getBillNumber());

		if (existsBookingBill) {
			throw new IllegalBookingBillArgumentsException(
					"La factura con número [ " + bookingBillToAdd.getBillNumber() + " ya existe.");
		}

		return bookingBillRepo.save(bookingBillToAdd);
	}

	@Override
	public BookingBillModel updateBookingBill(final String billNumber, final BookingBillModel bookingBillToUpdate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteBookingBill(final String billNumber) {
		if (!isStringNotBlank(billNumber)) {
			throw new IllegalBookingBillArgumentsException("El número de factura [ " + billNumber + " ] no es válido.");
		}

		boolean existsBookingBill = bookingBillRepo.existsById(billNumber);

		if (!existsBookingBill) {
			throw new BookingBillNotFoundException("No existe una factura con número [ " + billNumber + " ].");
		}

		bookingBillRepo.deleteById(billNumber);
	}

	@Override
	public List<BookingBillModel> findAllBookingBillsByAccomodation(final String accomodationRegNumber) {
		if (!isStringNotBlank(accomodationRegNumber)) {
			throw new IllegalAccomodationArgumentsException(
					"El número de registro [ " + accomodationRegNumber + " ] no es válido.");
		}

		/**
		 * Listado de facturas de reserva de un alojamiento
		 * <code>accomodationRegNumber</code>.
		 */
		String findAllBookingBillsByAccRegisterNumber = "SELECT bbm "
				+ "FROM BookingModel bm INNER JOIN bm.idAccomodation ac INNER JOIN bm.billNumber bbm "
				+ "WHERE ac.registerNumber = :regNumber";

		TypedQuery<BookingBillModel> bookings = em.createQuery(findAllBookingBillsByAccRegisterNumber,
				BookingBillModel.class);

		bookings.setParameter("regNumber", accomodationRegNumber);

		return bookings.getResultList();
	}
}
