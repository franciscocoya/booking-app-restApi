package com.hosting.rest.api.services.Payment;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hosting.rest.api.models.Payment.PaymentModel;
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
	public PaymentModel updatePaymentById(final PaymentModel paymentModel) {
		// TODO Auto-generated method stub

		return null;
	}

	@Override
	public void removePaymentById(final Integer paymentId) {
		paymentRepo.deleteById(paymentId);
	}

	@Override
	public List<PaymentModel> listAllPayments() {
		return paymentRepo.findAll();
	}

	@Override
	public PaymentModel getPaymentFromBooking(final Integer bookingId)
			throws IllegalArgumentException, NumberFormatException {
		// TODO: Obtener el método de pago de una reserva pasada como parametro
		// Mejorar querie para obtener el método de paso en cuestión.
		String findByBookingIdQuery = "select pm"
				+ " from BookingBillModel bbm inner join bbm.paymentId pm, BookingModel bm"
				+ " where bm.id = :bookingId and bm.billNumber.billNumber = bbm.billNumber.billNumber";

		TypedQuery<PaymentModel> payment = em.createQuery(findByBookingIdQuery, PaymentModel.class);

		payment.setParameter("bookingId", bookingId);

		return payment.getSingleResult();

	}

//	private EntityManager getEntityManager() {
//		return entityManager;
//	}

}
