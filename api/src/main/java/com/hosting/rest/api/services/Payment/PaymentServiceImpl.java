package com.hosting.rest.api.services.Payment;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hosting.rest.api.exceptions.NotFound.NotFoundCustomException;
import com.hosting.rest.api.models.Payment.PaymentModel;
import com.hosting.rest.api.models.Payment.PaymentPaypalModel;
import com.hosting.rest.api.repositories.Payment.IPaymentRepository;

@Service
public class PaymentServiceImpl implements IPaymentService {

	@PersistenceContext
	private EntityManager em;

	@Autowired
	private IPaymentRepository paymentRepo;

	@Override
	public PaymentModel addNewPayment(final PaymentModel paymentModel) {
		return paymentRepo.save(paymentModel);
	}

	@Override
	public PaymentModel updatePaymentById(final Integer paymentId, final PaymentModel paymentModel)
			throws NumberFormatException {
		boolean existsPayment = paymentRepo.existsById(paymentModel.getIdPayment());

		if (!existsPayment) {
			throw new NotFoundCustomException("El método de pago a actualizar no existe.");
		}

		PaymentModel originalPayment = paymentRepo.findById(paymentId).get();

		// Si el método de pago es Paypal - Actualizar email de la cuenta
		if (originalPayment instanceof PaymentPaypalModel) {
//			((PaymentPaypalModel) originalPayment).setAccountEmail(paymentModel.get);
		}

		// Si el método de pago es tarjeta de crédito - Actualizar el número de tarjeta

		return null;
	}

	@Override
	public void removePaymentById(final Integer paymentId) {
		boolean existsPayment = paymentRepo.existsById(paymentId);

		if (!existsPayment) {
			throw new NotFoundCustomException("El método de pago a eliminar no existe.");
		}

		paymentRepo.deleteById(paymentId);
	}

	@Override
	public List<PaymentModel> findAllPayments() {
		return paymentRepo.findAll();
	}

	@Override
	public PaymentModel findByBookingId(final Integer bookingId)
			throws IllegalArgumentException, NumberFormatException {

		String findByBookingIdQuery = "SELECT pm " + "FROM BookingModel bm INNER JOIN bm.idPayment pm "
				+ "WHERE bm.id = :bookingId";

		TypedQuery<PaymentModel> payment = em.createQuery(findByBookingIdQuery, PaymentModel.class);

		payment.setParameter("bookingId", bookingId);

		return payment.getSingleResult();

	}

}
