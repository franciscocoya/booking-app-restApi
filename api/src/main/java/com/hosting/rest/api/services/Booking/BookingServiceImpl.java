package com.hosting.rest.api.services.Booking;

import static com.hosting.rest.api.Utils.AppUtils.isBigDecimalValid;
import static com.hosting.rest.api.Utils.AppUtils.isIntegerValidAndPositive;
import static com.hosting.rest.api.Utils.AppUtils.isNotNull;
import static com.hosting.rest.api.Utils.AppUtils.isStringNotBlank;
import static com.hosting.rest.api.Utils.MathUtils.MATH_CONTEXT;
import static com.hosting.rest.api.Utils.ServiceParamValidator.validateParam;
import static com.hosting.rest.api.Utils.ServiceParamValidator.validateParamNotFound;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hosting.rest.api.models.Booking.BookingModel;
import com.hosting.rest.api.models.Booking.BookingStatus;
import com.hosting.rest.api.repositories.Accomodation.IAccomodationRepository;
import com.hosting.rest.api.repositories.Booking.IBookingRepository;
import com.hosting.rest.api.repositories.User.IUserRepository;
import com.hosting.rest.api.repositories.User.UserHost.IUserHostRepository;

/**
 * 
 * @author Francisco Coya Abajo
 * @version v1.0.2
 * @apiNote Controlador para gestionar las acciones de las reservas de la
 *          aplicación.
 * 
 */
@Service
public class BookingServiceImpl implements IBookingService {

	@Autowired
	private IBookingRepository bookingRepo;

	@Autowired
	private IAccomodationRepository accomodationRepo;

	@Autowired
	private IUserRepository userRepo;

	@Autowired
	private IUserHostRepository userHostRepo;

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
		// Validar reserva pasada como parametro
		validateParam(isNotNull(bookingToAdd), "Alguno de los valores de la reserva no es válido.");

		// checkBookingDates(bookingToAdd);

		// Calcular la comisión
		BigDecimal newServiceFee = calculateBookingServiceFee(bookingToAdd.getAmount());
		// bookingToAdd.setServiceFee(newServiceFee);

		// Calcular el total de la reserva (Comisiones y descuentos Incl.)
		BigDecimal total = calculateBookingTotalCost(bookingToAdd.getAmount(), bookingToAdd.getDisccount(),
				newServiceFee);

		BookingModel newBooking = new BookingModel(null, bookingToAdd.getCheckIn(), bookingToAdd.getCheckOut(),
				bookingToAdd.getNumOfGuests(), bookingToAdd.getAmount(), bookingToAdd.getDisccount(), newServiceFee,
				total, BookingStatus.PENDIENTE, bookingToAdd.getIdUser(), bookingToAdd.getIdAccomodation(),
				bookingToAdd.getIdPayment(), LocalDateTime.now());

		return bookingRepo.save(newBooking);
	}

	/**
	 * Comprueba que las fechas de una reserva pasada como parámetro estén
	 * disponibles.
	 * 
	 * @param bookingToCheck
	 * @deprecated
	 * @return
	 */
//	private boolean checkBookingDates(final BookingModel bookingToCheck) {
//		Set<List<LocalDateTime>> reservedDates = checkAccomodationAvailability(
//				bookingToCheck.getIdAccomodation().getRegisterNumber());
//
//		LocalDateTime checkIn = bookingToCheck.getCheckIn();
//		LocalDateTime checkOut = bookingToCheck.getCheckOut();
//
//		for (List<LocalDateTime> date : reservedDates) {
//			LocalDateTime checkInReserved = date.get(0);
//			LocalDateTime checkOutReserved = date.get(1);
//
//			boolean isCheckInValid = (checkIn.isEqual(checkInReserved) || checkIn.isEqual(checkOutReserved))
//					|| (checkIn.isBefore(checkOutReserved) && checkIn.isAfter(checkOutReserved));
//
//			boolean isCheckOutValid = (checkOut.isEqual(checkInReserved) || checkOut.isEqual(checkOutReserved))
//					|| (checkOut.isBefore(checkOutReserved) && checkOut.isAfter(checkOutReserved));
//
//			if (!(isCheckInValid && isCheckOutValid)) {
//				return false;
//			}
//		}
//		return true;
//	}

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
	 * 
	 * @throws NumberFormatException Si el id de la reserva no es un número.
	 */
	@Transactional
	@Override
	public BookingModel updateBookingDataById(final Integer bookingId, final BookingModel bookingToUpdate)
			throws NumberFormatException {
		// Validar id de la reserva
		validateParam(isIntegerValidAndPositive(bookingId),
				"El id de reserva [ " + bookingId + " ] a actualizar no es válido.");

		// Validar reserva pasada como parametro.
		validateParam(isNotNull(bookingToUpdate), "Alguno de los datos de la reserva a actualizar no es válido.");

		// Comprobar si existe la reserva
		validateParamNotFound(bookingRepo.existsById(bookingId), "No existe una reserva con el id " + bookingId);

		BookingModel originalBooking = bookingRepo.getById(bookingId);

		// Número de huéspedes
		originalBooking.setNumOfGuests(bookingToUpdate.getNumOfGuests());

		// BookingAmount = price_per_night * days * guests
		BigDecimal updatedAmount = bookingToUpdate.getAmount();

		// Validar el coste updateAmount obtenido
		validateParam(isBigDecimalValid(updatedAmount, true), "Es coste de reserva (Sin comisiones) no es válido.");

		originalBooking.setAmount(bookingToUpdate.getAmount());

		// Descuento a aplicar. 0 si no se aplica descuento.
		BigDecimal updatedDisccount = bookingToUpdate.getDisccount();

		// Validar el descuento obtenido
		validateParam(isBigDecimalValid(updatedDisccount, true), "Es descuento obtenido no es válido.");

		originalBooking.setDisccount(updatedDisccount);

		// Recalcular SERVICE_FEE y TOTAL.
		BigDecimal updatedServiceFee = calculateBookingServiceFee(updatedAmount);

		// Validar la comisión resultante
		validateParam(isBigDecimalValid(updatedServiceFee, true), "La comisión obtenido no es válida.");

		originalBooking.setServiceFee(updatedServiceFee);

		// Recalcular el coste total de la reserva.
		BigDecimal updatedBookingTotal = calculateBookingTotalCost(updatedAmount, updatedDisccount, updatedServiceFee);

		// Validar es coste total calculado
		validateParam(isBigDecimalValid(updatedBookingTotal, true), "El coste total resultante no es válido.");

		originalBooking.setTotal(updatedBookingTotal);

		return bookingRepo.save(originalBooking);
	}

	/**
	 * Calcula la comisión de la aplicación en base al precio de la reserva
	 * <code>bookingAmount</code>.
	 * 
	 * @param bookingAmount
	 * @param serviceFee
	 * 
	 * @return
	 * 
	 * @throws NumberFormatException Si <code>bookingAmount</code> no es un número o
	 *                               si <code>serviceFee</code> no es un número
	 */
	private BigDecimal calculateBookingServiceFee(final BigDecimal bookingAmount, final BigDecimal serviceFee)
			throws NumberFormatException {
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
	 * 
	 * @throws NumberFormatException Si <code>bookingAmount</code> no es un número.
	 */
	private BigDecimal calculateBookingServiceFee(final BigDecimal bookingAmount) throws NumberFormatException {
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
	 * 
	 * @throws NumberFormatExceptionSi <code>bookingAmount</code> no es un número o
	 *                                 <code>bookingDisccountPercentage</code> no es
	 *                                 un número o <code>bookingServiceFee</code> no
	 *                                 es un número
	 */
	private BigDecimal calculateBookingTotalCost(final BigDecimal bookingAmount,
			final BigDecimal bookingDisccountPercentage, final BigDecimal bookingServiceFee)
			throws NumberFormatException {

		BigDecimal newDisccount = bookingDisccountPercentage == null ? BigDecimal.ZERO : bookingDisccountPercentage;

		BigDecimal disccountResult = bookingAmount.multiply(newDisccount.divide(new BigDecimal("100"), MATH_CONTEXT),
				MATH_CONTEXT);

		return bookingAmount.subtract(disccountResult, MATH_CONTEXT).add(bookingServiceFee, MATH_CONTEXT);
	}

	/**
	 * Borrado de una reserva con id <code>bookingId</code>.
	 * 
	 * @param bookingId
	 * 
	 * @throws NumberFormatException Si <code>bookingId</code> no es un número.
	 */
	@Override
	public void deleteBookingById(final Integer bookingId) throws NumberFormatException {

		// Validar id de reserva
		validateParam(isIntegerValidAndPositive(bookingId),
				"El id de reserva [ " + bookingId + " ] a eliminar no es válido.");

		// Comprobar si existe la reserva
		validateParamNotFound(bookingRepo.existsById(bookingId),
				"La reserva con id [ " + bookingId + " ] a eliminar no existe.");

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
	 * @throws NumberFormatException Si <code>yearToSearch</code> no es un número.
	 */
	@Override
	public List<BookingModel> findByBookingYear(final String regNumber, final Integer yearToSearch)
			throws NumberFormatException {
		// Validar número de registro de alojamiento
		validateParam(isStringNotBlank(regNumber), "El id de alojamiento está vacío.");

		// Validar año a buscar
		validateParam(isIntegerValidAndPositive(yearToSearch),
				"El año [ " + yearToSearch + " ] introducido no es válido.");

		// Comprobar si existe el alojamiento
		validateParamNotFound(accomodationRepo.existsById(regNumber),
				"No existe un alojamiento con el número de registro " + regNumber);

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
	 * 
	 * @throws NumberFormatException Si el id de usuario no es un número.
	 */
	@Override
	public List<BookingModel> findAllBookingByUser(final Integer userId) throws NumberFormatException {
		// Validar id de usuario
		validateParam(isIntegerValidAndPositive(userId), "El id de usuario [ " + userId + " ] no es válido.");

		// Comprobar si existe el usuario
		validateParamNotFound(userRepo.existsById(userId), "No existe un usuario con id " + userId);

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
	 * 
	 * @throws NumberFormatException Si <code>bookingId</code> no es un número.
	 */
	@Override
	public BookingModel getBookingById(final Integer bookingId) throws NumberFormatException {
		// Validar Id de reserva
		validateParam(isIntegerValidAndPositive(bookingId), "El id de reserva [ " + bookingId + " ] no es válido.");

		return bookingRepo.findById(bookingId).get();
	}

	/**
	 * Número de reservas realizadas por el usuario con id <code>userId</code>.
	 * 
	 * @param userId
	 * 
	 * @return
	 * 
	 * @throws NumberFormatException Si el id de usuario no es un número.
	 */
	@Override
	public int getNumOfBookingsByUserId(final Integer userId) throws NumberFormatException {
		// Validar id de usuario
		validateParam(isIntegerValidAndPositive(userId), "El id de usuario [ " + userId + " ] no es un número.");

		return findAllBookingByUser(userId).size();
	}

	/**
	 * Listado de las fechas de reserva del alojamiento con el número de registro
	 * pasado como parámetro.
	 * 
	 * @return .
	 */
	@Override
	public Set<List<LocalDateTime>> checkAccomodationAvailability(final String regNumber) {

		Set<List<LocalDateTime>> bookingDates = new HashSet<List<LocalDateTime>>();

		// Validar número de registro del alojamiento
		validateParam(isStringNotBlank(regNumber), "El número de registro del alojamiento no es válido.");

		// Comprobar si existe el alojamiento
		validateParamNotFound(accomodationRepo.existsById(regNumber),
				"No existe un alojamiento con número de registro " + regNumber);

		String listAllAvaibleBookingDatesByRegNumberQuery = "SELECT bm " + "FROM BookingModel bm "
				+ "WHERE bm.idAccomodation.registerNumber = :regNumber " + "AND bm.bookingStatus = '"
				+ BookingStatus.CONFIRMADA + "' " + "OR bm.bookingStatus = '" + BookingStatus.PENDIENTE + "' "
				+ "OR bm.bookingStatus = '" + BookingStatus.COMPLETADA + "'"
				+ "OR bm.bookingStatus = '" + BookingStatus.CONFIRMADA + "'";

		TypedQuery<BookingModel> listAvailableBookingDates = em.createQuery(listAllAvaibleBookingDatesByRegNumberQuery,
				BookingModel.class);

		listAvailableBookingDates.setParameter("regNumber", regNumber);

		List<BookingModel> accomodationBookings = listAvailableBookingDates.getResultList();

		for (BookingModel bm : accomodationBookings) {
			List<LocalDateTime> dates = new ArrayList<LocalDateTime>();
			dates.add(bm.getCheckIn());
			dates.add(bm.getCheckOut());

			bookingDates.add(dates);
		}

		return bookingDates;
	}

	/**
	 * Listado de todas las reservas que tienen los alojamientos de un usuario host.
	 * 
	 * @param userId
	 */
	@Override
	public List<BookingModel> findAllBookingFromHostAccomodations(final Integer userId) {

		// Validar id de usuario
		validateParam(isIntegerValidAndPositive(userId), "El id de usuario no es válido");

		// Comprobar si existe el usuario
		validateParamNotFound(userHostRepo.existsById(userId), "No existe el usuario especificado");

		String findAllBookingByUserQuery = "SELECT bm from BookingModel bm INNER JOIN bm.idAccomodation am "
				+ "WHERE am.idUserHost.id = :userId";

		TypedQuery<BookingModel> bookings = em.createQuery(findAllBookingByUserQuery, BookingModel.class)
				.setParameter("userId", userId);

		return bookings.getResultList();
	}

	/**
	 * Actualización del estado de una reserva.
	 * 
	 * @param bookingId
	 * @param newBookingStatus
	 * 
	 * @return
	 * 
	 * @throws NumberFormatException Si el id de la reserva no es un número.
	 */
	@Transactional
	@Override
	public void updateBookingStatus(final Integer bookingId, final BookingStatus newBookingStatus)
			throws NumberFormatException {
		// Validar id de reserva
		validateParam(isIntegerValidAndPositive(bookingId), "El id de reserva [ " + bookingId + " ] no es válido.");

		// Comprobar si existe la reserva
		validateParamNotFound(bookingRepo.existsById(bookingId), "No existe la reserva a actualizar.");

		// Validar nuevo estado de reserva
		validateParam(isNotNull(newBookingStatus), "El nuevo estado para la reserva no es válido.");

		em.createQuery("UPDATE BookingModel bm SET bm.bookingStatus = :bookingStatus WHERE id = :bookingId")
				.setParameter("bookingId", bookingId).setParameter("bookingStatus", newBookingStatus).executeUpdate();
	}
}
