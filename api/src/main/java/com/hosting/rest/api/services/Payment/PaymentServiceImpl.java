package com.hosting.rest.api.services.Payment;

import static com.hosting.rest.api.Utils.AppUtils.isIntegerValidAndPositive;
import static com.hosting.rest.api.Utils.AppUtils.isNotNull;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hosting.rest.api.exceptions.IllegalArguments.IllegalArgumentsCustomException;
import com.hosting.rest.api.exceptions.NotFound.NotFoundCustomException;
import com.hosting.rest.api.models.Payment.PaymentModel;
import com.hosting.rest.api.repositories.Booking.IBookingRepository;
import com.hosting.rest.api.repositories.Payment.IPaymentRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author Francisco Coya Abajo
 * @version v1.0.1
 * @apiNote Controlador para gestionar las acciones de los métodos de pago de la
 *          aplicación.
 * 
 */
@Service
@Slf4j
public class PaymentServiceImpl implements IPaymentService {

	@PersistenceContext
	private EntityManager em;

	@Autowired
	private IPaymentRepository paymentRepo;

	@Autowired
	private IBookingRepository bookingRepo;

	/**
	 * Creación de un nuevo método de pago.
	 * 
	 * @param paymentModel
	 * 
	 * @return
	 */
	@Override
	public PaymentModel addNewPayment(final PaymentModel paymentModel) {
		if (!isNotNull(paymentModel)) {
			log.error("Alguno de los valores introducido para el método de pago no es válido.");
			throw new IllegalArgumentsCustomException(
					"Alguno de los valores introducido para el método de pago no es válido.");
		}

		return paymentRepo.save(paymentModel);
	}

	/**
	 * Borrado de un método de pago con id <code>paymentId</code>.
	 * 
	 * @param paymentId
	 * 
	 * @throws NumberFormatException Si el id del método de pago no es un número.
	 */
	@Override
	public void removePaymentById(final Integer paymentId) throws NumberFormatException {
		boolean existsPayment = paymentRepo.existsById(paymentId);

		if (!isIntegerValidAndPositive(paymentId)) {
			log.error("El id del método de pago a eliminar no es válido.");
			throw new IllegalArgumentsCustomException("El id del método de pago a eliminar no es válido.");
		}

		if (!existsPayment) {
			log.error("El método de pago a eliminar no existe.");
			throw new NotFoundCustomException("El método de pago a eliminar no existe.");
		}

		paymentRepo.deleteById(paymentId);
	}

	/**
	 * Listado de todos los métodos de pago aceptados en la aplicación.
	 *
	 * @return
	 */
	@Override
	public List<PaymentModel> findAllPayments() {
		return paymentRepo.findAll();
	}

	/**
	 * Obtención del método de pago empleado en la reserva con id
	 * <code>bookingId</code>.
	 * 
	 * @param bookingId
	 * 
	 * @return
	 * 
	 * @throws NumberFormatException
	 */
	@Override
	public PaymentModel findByBookingId(final Integer bookingId) throws NumberFormatException {

		if (!isIntegerValidAndPositive(bookingId)) {
			log.error("El id de la reserva a buscar no es válido.");
			throw new IllegalArgumentsCustomException("El id de la reserva a buscar no es válido.");
		}

		String findByBookingIdQuery = "SELECT pm " + "FROM BookingModel bm INNER JOIN bm.idPayment pm "
				+ "WHERE bm.id = :bookingId";

		// Comprobar que existe la reserva
		if (!bookingRepo.existsById(bookingId)) {
			log.error("No existe una reserva con id " + bookingId);
			throw new NotFoundCustomException("No existe una reserva con id " + bookingId);
		}

		TypedQuery<PaymentModel> payment = em.createQuery(findByBookingIdQuery, PaymentModel.class);

		payment.setParameter("bookingId", bookingId);

		return payment.getSingleResult();
	}

}
