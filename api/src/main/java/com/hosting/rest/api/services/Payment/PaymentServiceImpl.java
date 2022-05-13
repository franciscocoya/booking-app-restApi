package com.hosting.rest.api.services.Payment;

import static com.hosting.rest.api.Utils.AppUtils.isIntegerValidAndPositive;
import static com.hosting.rest.api.Utils.ServiceParamValidator.validateParam;
import static com.hosting.rest.api.Utils.ServiceParamValidator.validateParamNotFound;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hosting.rest.api.models.Payment.PaymentModel;
import com.hosting.rest.api.repositories.Booking.IBookingRepository;
import com.hosting.rest.api.repositories.Payment.IPaymentRepository;

/**
 * 
 * @author Francisco Coya Abajo
 * @version v1.0.3
 * @apiNote Controlador para gestionar las acciones de los métodos de pago de la
 *          aplicación.
 * 
 */
@Service
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
	public PaymentModel addNewPayment() {
		return paymentRepo.save(new PaymentModel(getLastPaymentId() + 1, null, null));
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

		// Validar id del método de pago
		validateParam(isIntegerValidAndPositive(paymentId), "El id del método de pago a eliminar no es válido.");

		// Comprobar si existe
		validateParamNotFound(paymentRepo.existsById(paymentId), "El método de pago a eliminar no existe.");

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
	 * @throws NumberFormatException Si el id de reserva no es un número.
	 */
	@Override
	public PaymentModel findByBookingId(final Integer bookingId) throws NumberFormatException {
		// Validar id de la reserva
		validateParam(isIntegerValidAndPositive(bookingId), "El id de la reserva a buscar no es válido.");

		// Comprobar que existe la reserva
		validateParamNotFound(bookingRepo.existsById(bookingId), "No existe una reserva con id " + bookingId);

		String findByBookingIdQuery = "SELECT pm " + "FROM BookingModel bm INNER JOIN bm.idPayment pm "
				+ "WHERE bm.id = :bookingId";

		TypedQuery<PaymentModel> payment = em.createQuery(findByBookingIdQuery, PaymentModel.class);

		payment.setParameter("bookingId", bookingId);

		return payment.getSingleResult();
	}

	@Override
	public Integer getLastPaymentId() {
		return paymentRepo.findAll().size();
	}

}
