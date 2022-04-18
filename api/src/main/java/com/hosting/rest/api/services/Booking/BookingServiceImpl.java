package com.hosting.rest.api.services.Booking;

import static com.hosting.rest.api.Utils.AppUtils.isIntegerValidAndPositive;
import static com.hosting.rest.api.Utils.AppUtils.isNotNull;
import static com.hosting.rest.api.Utils.AppUtils.isStringNotBlank;
import static com.hosting.rest.api.Utils.MathUtils.MATH_CONTEXT;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	 * Creación de una nueva reserva con los datos que contiene el modelo
	 * <code>bookingToAdd</code>
	 * 
	 * @see #calculateBookingServiceFee(BigDecimal)
	 * @see #calculateBookingTotalCost(BigDecimal, BigDecimal, BigDecimal)
	 * 
	 * @param bookingModelToCreate
	 * 
	 * @return
	 */
	@Override
	public BookingModel addNewBooking(final BookingModel bookingToAdd) {
		if (!isNotNull(bookingToAdd)) {
			log.error("Alguno de los valores de la reserva no es válido.");
			throw new IllegalArgumentsCustomException("Alguno de los valores de la reserva no es válido.");
		}

		// Comprobar si existe la reserva
		if (bookingRepo.existsById(bookingToAdd.getId())) {
			log.error("La reserva con id [ " + bookingToAdd.getId() + " ] ya existe.");
			throw new IllegalArgumentsCustomException("La reserva con id [ " + bookingToAdd.getId() + " ] ya existe.");
		}

		// Calcular la comisión
		BigDecimal newServiceFee = calculateBookingServiceFee(bookingToAdd.getAmount());
		bookingToAdd.setServiceFee(newServiceFee);

		// Calcular el total de la reserva (Comisiones y descuentos Incl.)

		BigDecimal newDisccount = bookingToAdd.getDisccount() == null ? BigDecimal.ZERO : bookingToAdd.getDisccount();

		bookingToAdd.setTotal(calculateBookingTotalCost(bookingToAdd.getAmount(), newDisccount, newServiceFee));

		return bookingRepo.save(bookingToAdd);
	}

	/**
	 * Actualización de los datos de una reserva con id <code>bookingId</code>.
	 * 
	 * Al actualizar los datos, es necesario recalcular los importes de SERVICE_FEE
	 * y TOTAL.
	 * 
	 * SERVICE_FEE = AMOUNT * 0.10 TOTAL = AMOUNT + SERVICE_FEE - DISCCOUNT
	 * 
	 * @see #calculateBookingAmount(BigDecimal, LocalDateTime, LocalDateTime,
	 *      Integer)
	 * 
	 * @see #calculateBookingServiceFee(BigDecimal, BigDecimal)
	 * 
	 * @see #calculateBookingTotalCost(BigDecimal, BigDecimal, BigDecimal)
	 * 
	 * @param bookingId
	 * @param bookingToUpdate
	 * 
	 * @return
	 */
	@Transactional
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

		// Comprobar si existe la reserva
		if (!bookingRepo.existsById(bookingId)) {
			log.error("No existe una reserva con el id " + bookingId);
			throw new NotFoundCustomException("No existe una reserva con el id " + bookingId);
		}

		BookingModel originalBooking = bookingRepo.getById(bookingId);

		// Número de huéspedes
		originalBooking.setNumOfGuests(bookingToUpdate.getNumOfGuests());

		// Recalcular el coste (Antes de comisiones y descuentos)
		BigDecimal accomodationPricePerNight = bookingToUpdate.getIdAccomodation().getPricePerNight();

		// BookingAmount = price_per_night * days * guests
		BigDecimal updatedBookingAmount = calculateBookingAmount(accomodationPricePerNight,
				bookingToUpdate.getCheckOut(), bookingToUpdate.getCheckIn(), bookingToUpdate.getNumOfGuests());

		originalBooking.setAmount(updatedBookingAmount);

		// Descuento a aplicar. 0 si no se aplica descuento.
		BigDecimal updatedDisccount = bookingToUpdate.getDisccount();
		originalBooking.setDisccount(updatedDisccount);

		// Recalcular SERVICE_FEE y TOTAL.
		BigDecimal updatedServiceFee = calculateBookingServiceFee(updatedBookingAmount);
		originalBooking.setServiceFee(updatedServiceFee);

		// Recalcular el coste total de la reserva.
		BigDecimal updatedBookingTotal = calculateBookingTotalCost(updatedBookingAmount, updatedDisccount,
				updatedServiceFee);
		originalBooking.setTotal(updatedBookingTotal);

		return bookingRepo.save(originalBooking);
	}

	/**
	 * Calcula el coste de la reserva antes de aplicar descuentos y comisiones.
	 * 
	 * @param pricePerNight
	 * @param bookingCheckIn
	 * @param bookingCheckOut
	 * @param guests
	 * 
	 * @return
	 */
	private BigDecimal calculateBookingAmount(final BigDecimal pricePerNight, final LocalDateTime bookingCheckIn,
			final LocalDateTime bookingCheckOut, final Integer guests) {
		BigDecimal bookingAmount = BigDecimal.ZERO;

		Integer bookingDays = (int) Duration.between(bookingCheckOut, bookingCheckIn).toDays();

		bookingAmount = pricePerNight.multiply(new BigDecimal(bookingDays, MATH_CONTEXT), MATH_CONTEXT);

		return bookingAmount;
	}

	/**
	 * Calcula la comisión de la aplicación en base al precio de la reserva
	 * <code>bookingAmount</code>.
	 * 
	 * @param bookingAmount
	 * @param serviceFee
	 * 
	 * @return
	 */
	private BigDecimal calculateBookingServiceFee(final BigDecimal bookingAmount, final BigDecimal serviceFee) {
		return bookingAmount.multiply(serviceFee, MATH_CONTEXT);
	}

	/**
	 * Calcula el importe de la comisión aplicada. La comisión aplicada es por
	 * defecto {@link #DEFAULT_APP_SERVICE_FEE}
	 * 
	 * @see #calculateBookingServiceFee(BigDecimal, BigDecimal)
	 * @param bookingAmount
	 * 
	 * @return
	 */
	private BigDecimal calculateBookingServiceFee(final BigDecimal bookingAmount) {
		return calculateBookingServiceFee(DEFAULT_APP_SERVICE_FEE, bookingAmount);
	}

	/**
	 * Calcula el coste total de la reserva del alojamiento, incluyendo descuentos y
	 * comisiones.
	 * 
	 * @param bookingAmount
	 * @param bookingDisccount
	 * @param bookingServiceFee
	 * 
	 * @return
	 */
	private BigDecimal calculateBookingTotalCost(final BigDecimal bookingAmount,
			final BigDecimal bookingDisccountPercentage, final BigDecimal bookingServiceFee) {
		BigDecimal disccountResult = bookingAmount
				.multiply(bookingDisccountPercentage.divide(new BigDecimal("100"), MATH_CONTEXT), MATH_CONTEXT);

		return bookingAmount.subtract(disccountResult, MATH_CONTEXT).add(bookingServiceFee, MATH_CONTEXT);
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
