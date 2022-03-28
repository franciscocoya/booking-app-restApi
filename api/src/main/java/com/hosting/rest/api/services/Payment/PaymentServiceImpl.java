package com.hosting.rest.api.services.Payment;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hosting.rest.api.models.Payment.PaymentModel;
import com.hosting.rest.api.repositories.Payment.IPaymentRepository;

@Service
public class PaymentServiceImpl implements IPaymentService {
	
//	@PersistenceContext
//	private EntityManager entityManager;
	
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
	public PaymentModel getPaymentFromBooking(final Integer bookingId) {
		// TODO: Completar query
//		String getPaymentFromBookingQuery = "SELECT p FROM PaymentModel p  WHERE  ";
//		TypedQuery<PaymentModel> paymentToReturn = getEntityManager().createQuery(getPaymentFromBookingQuery, PaymentModel.class);
//		
//		return paymentToReturn.getSingleResult();
		return null;
	}
	
//	private EntityManager getEntityManager() {
//		return entityManager;
//	}

}
