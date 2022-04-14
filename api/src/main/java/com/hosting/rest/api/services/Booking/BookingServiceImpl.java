package com.hosting.rest.api.services.Booking;

import static com.hosting.rest.api.Utils.AppUtils.isIntegerValidAndPositive;
import static com.hosting.rest.api.Utils.AppUtils.isNotNull;
import static com.hosting.rest.api.Utils.AppUtils.isStringNotBlank;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hosting.rest.api.exceptions.IllegalArguments.IllegalArgumentsCustomException;
import com.hosting.rest.api.exceptions.NotFound.NotFoundCustomException;
import com.hosting.rest.api.models.Booking.BookingModel;
import com.hosting.rest.api.repositories.Accomodation.IAccomodationRepository;
import com.hosting.rest.api.repositories.Booking.IBookingRepository;
import com.hosting.rest.api.repositories.User.IUserRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author Francisco Coya Abajo
 * @version v1.0.2
 * @apiNote Controlador para gestionar las acciones de las reservas de la
 *          aplicación.
 * 
 */
@Service
@Slf4j
public class BookingServiceImpl implements IBookingService {

	@Autowired
	private IBookingRepository bookingRepo;

	@Autowired
	private IAccomodationRepository accomodationRepo;

	@Autowired
	private IUserRepository userRepo;

	@PersistenceContext
	private EntityManager em;

	/**
	 * Listado de las reservas realizadas durante un año en el alojamiento con id
	 * <code>regNumber</code>.
	 * 
	 * @param regNumber
	 * @param yearToSearch
	 * 
	 * @return
	 */
	@Override
	public List<BookingModel> findByBookingYear(final String regNumber, final Integer yearToSearch) {

		if (!isStringNotBlank(regNumber)) {
			log.error("El id de alojamiento está vacío.");
			throw new IllegalArgumentsCustomException("El id de alojamiento está vacío.");
		}

		if (!isIntegerValidAndPositive(yearToSearch)) {
			log.error("El año introducido no es válido.");
			throw new IllegalArgumentsCustomException("El año introducido no es válido.");
		}

		// Comprobar si existe el alojamiento
		if (!accomodationRepo.existsById(regNumber)) {
			log.error("No existe un alojamiento con el número de registro " + regNumber);
			throw new NotFoundCustomException("No existe un alojamiento con el número de registro " + regNumber);
		}

		String listBookingFromYearQuery = "SELECT bm " + "FROM BookingModel bm INNER JOIN bm.idAccomodation ac "
				+ "WHERE YEAR(bm.createdAt) = :year AND ac.registerNumber = :registerNumber";

		TypedQuery<BookingModel> bookings = em.createQuery(listBookingFromYearQuery, BookingModel.class);

		bookings.setParameter("year", yearToSearch);
		bookings.setParameter("registerNumber", regNumber);

		return bookings.getResultList();
	}

	/**
	 * Creación de una nueva reserva con los datos pasados como parámetro en
	 * <code>bookingModelToCreate</code>.
	 * 
	 * @param bookingModelToCreate
	 * 
	 * @return
	 */
	@Override
	public BookingModel addNewBooking(final BookingModel bookingModelToCreate) {
		if (!isNotNull(bookingModelToCreate)) {
			log.error("Los datos de la reserva a crear no son válidos");
			throw new IllegalArgumentsCustomException("Los datos de la reserva a crear no son válidos.");
		}

		// Comprobar si existe una reserva con esos datos
		if (bookingRepo.existsById(bookingModelToCreate.getId())) {
			log.error("Ya existe una reserva con el id " + bookingModelToCreate.getId());
			throw new IllegalArgumentsCustomException(
					"Ya existe una reserva con el id " + bookingModelToCreate.getId());
		}

		return bookingRepo.save(bookingModelToCreate);
	}

	/**
	 * Actualización de los datos de una reserva con id <code>bookingId</code>.
	 * 
	 * @param bookingId
	 * @param bookingToUpdate
	 * 
	 * @return
	 */
	@Override
	public BookingModel updateBookingDataById(final Integer bookingId, final BookingModel bookingToUpdate) {

		if (!isIntegerValidAndPositive(bookingId)) {
			log.error("El id de reserva [ " + bookingId + " ] a actualizar no es válido.");
			throw new IllegalArgumentsCustomException(
					"El id de reserva [ " + bookingId + " ] a actualizar no es válido.");
		}

		if (!isNotNull(bookingToUpdate)) {
			log.error("Alguno de los datos de la reserva a actualizar no es válido.");
			throw new IllegalArgumentsCustomException("Alguno de los datos de la reserva a actualizar no es válido.");
		}

		if (!bookingRepo.existsById(bookingId)) {
			log.error("No existe una reserva con el id " + bookingId);
			throw new NotFoundCustomException("No existe una reserva con el id " + bookingId);
		}

		// TODO: Actualizar la reserva

		return null;
	}

	/**
	 * Borrado de una reserva con id <code>bookingId</code>.
	 * 
	 * @param bookingId
	 */
	@Override
	public void deleteBookingById(final Integer bookingId) {

		if (!isIntegerValidAndPositive(bookingId)) {
			log.error("El id de reserva [ " + bookingId + " ] a eliminar no es válido.");
			throw new IllegalArgumentsCustomException(
					"El id de reserva [ " + bookingId + " ] a eliminar no es válido.");
		}

		// Comprobar si existe la reserva
		if (!bookingRepo.existsById(bookingId)) {
			log.error("La reserva con id [ " + bookingId + " ] a eliminar no existe.");
			throw new NotFoundCustomException("La reserva con id [ " + bookingId + " ] a eliminar no existe.");
		}

		bookingRepo.deleteById(bookingId);
	}

	/**
	 * Listado de todas las reservas realizadas por el usuario con id
	 * <code>userId</code>.
	 * 
	 * @param userId
	 * 
	 * @return
	 */
	@Override
	public List<BookingModel> findAllBookingByUser(final Integer userId) {

		if (!isIntegerValidAndPositive(userId)) {
			log.error("El id de usuario [ " + userId + " ] no es válido.");
			throw new IllegalArgumentsCustomException("El id de usuario [ " + userId + " ] no es válido.");
		}

		// Comprobar si existe el usuario
		if (!userRepo.existsById(userId)) {
			log.error("No existe un usuario con id " + userId);
			throw new NotFoundCustomException("No existe un usuario con id " + userId);
		}

		String findAllBookingsOfUserQuery = "SELECT bm " + "FROM BookingModel bm INNER JOIN bm.idUser host "
				+ "WHERE host.id = :userId";

		TypedQuery<BookingModel> userBookings = em.createQuery(findAllBookingsOfUserQuery, BookingModel.class);

		userBookings.setParameter("userId", userId);

		return userBookings.getResultList();
	}

	/**
	 * Obtención de la reserva con id <code>bookingId</code>.
	 * 
	 * @param bookingId
	 * 
	 * @return
	 */
	@Override
	public BookingModel getBookingById(final Integer bookingId) {
		if (!isIntegerValidAndPositive(bookingId)) {
			log.error("El id de reserva [ " + bookingId + " ] no es válido.");
			throw new IllegalArgumentsCustomException("El id de reserva [ " + bookingId + " ] no es válido.");
		}

		return bookingRepo.findById(bookingId).get();
	}

	/**
	 * Número de reservas realizadas por el usuario con id <code>userId</code>.
	 * 
	 * @param userId
	 * 
	 * @return
	 */
	@Override
	public int getNumOfBookingsByUserId(final Integer userId) {
		return findAllBookingByUser(userId).size();
	}
}
