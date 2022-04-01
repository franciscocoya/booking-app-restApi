package com.hosting.rest.api.services.Accomodation.AccomodationReview.AccomodationReviewReply;

import static com.hosting.rest.api.Utils.AppUtils.isIntegerValidAndPositive;
import static com.hosting.rest.api.Utils.AppUtils.isNotNull;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hosting.rest.api.exceptions.Accomodation.IllegalArguments.IllegalArgumentsCustomException;
import com.hosting.rest.api.models.Accomodation.AccomodationReview.AccomodationReviewReply.AccomodationReviewReplyModel;
import com.hosting.rest.api.repositories.Accomodation.AccomodationReview.AccomodationReviewReply.IAccomodationReviewReplyRepository;

@Service
public class AccomodationReviewReplyServiceImpl implements IAccomodationReviewReplyService {

	@PersistenceContext
	private EntityManager em;

	@Autowired
	private IAccomodationReviewReplyRepository accomodationReviewReplyRepo;

	@Override
	public AccomodationReviewReplyModel addNewAccomodationReviewReply(
			final AccomodationReviewReplyModel accomodationReviewReplyToAdd) {

		if (!isNotNull(accomodationReviewReplyToAdd)) {
			throw new IllegalArgumentsCustomException("La respuesta a la valoración del alojamiento no es válida.");
		}

		boolean existsAccomodationReviewReply = findByReviewId(
				accomodationReviewReplyToAdd.getAccomodationReview().getId()) != null;

		if (existsAccomodationReviewReply) {
			throw new IllegalArgumentsCustomException("La respuesta a la valoración del alojamiento ya existe");
		}

		return accomodationReviewReplyRepo.save(accomodationReviewReplyToAdd);
	}

	@Override
	public void deleteAccomodationReviewReplyById(final Integer accomodationReviewId) {
//		String deleteAccomodationReviewReplyQuery = "DELETE FROM AccomodationReviewReplyModel arrm "
//				+ "WHERE arrm.accomodationReview.id = :accomodationReviewId";

		if (!isIntegerValidAndPositive(accomodationReviewId)) {
			throw new IllegalArgumentsCustomException("El id de la valoración del alojamiento no es válido.");
		}

		// Obtención de la repuesta a partir del id de la valoración.
		AccomodationReviewReplyModel accomodationReviewReplyDeleted = findByReviewId(accomodationReviewId);

		if (accomodationReviewReplyDeleted != null) {
			accomodationReviewReplyRepo.delete(accomodationReviewReplyDeleted);
		}

//		Query accomodationReviewReplyDeleted = em.createQuery(deleteAccomodationReviewReplyQuery);
//
//		accomodationReviewReplyDeleted.setParameter("accomodationReviewId", accomodationReviewId);
//
//		accomodationReviewReplyDeleted.executeUpdate();
	}

	@Override
	public AccomodationReviewReplyModel findByReviewId(final Integer accomodationReviewId) {
		String findByReviewIdQuery = "SELECT arrm "
				+ "FROM AccomodationReviewReplyModel arrm INNER JOIN arrm.accomodationReview arm "
				+ "WHERE arm.id = :accomodationReviewId";

		TypedQuery<AccomodationReviewReplyModel> accomodationReviewReply = em.createQuery(findByReviewIdQuery,
				AccomodationReviewReplyModel.class);

		accomodationReviewReply.setParameter("accomodationReviewId", accomodationReviewId);

		return accomodationReviewReply.getSingleResult();
	}

}
