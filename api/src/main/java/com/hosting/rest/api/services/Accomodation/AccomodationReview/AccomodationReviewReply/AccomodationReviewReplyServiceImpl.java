package com.hosting.rest.api.services.Accomodation.AccomodationReview.AccomodationReviewReply;

import static com.hosting.rest.api.Utils.AppUtils.isNotNull;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hosting.rest.api.exceptions.Accomodation.AccomodationReview.AccomodationReviewReply.IllegalArgument.IllegalAccomodationReviewReplyArgumentsException;
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
			throw new IllegalAccomodationReviewReplyArgumentsException(
					"La respuesta a la valoración del alojamiento no es válida.");
		}

		boolean existsAccomodationReviewReply = findByReviewId(
				accomodationReviewReplyToAdd.getAccomodationReview().getId()) != null;

		if (existsAccomodationReviewReply) {
			throw new IllegalAccomodationReviewReplyArgumentsException(
					"La respuesta a la valoración del alojamiento ya existe");
		}

		return accomodationReviewReplyRepo.save(accomodationReviewReplyToAdd);
	}

	@Override
	public void deleteAccomodationReviewReplyById(final Integer accomodationReviewId) {
		String deleteAccomodationReviewReplyQuery = "DELETE FROM AccomodationReviewReplyModel arrm INNER JOIN arrm.accomodationReview arm"
				+ "WHERE arm.id = :accomodationReviewId";

		TypedQuery<Void> accomodationReviewReplyDeleted = em.createQuery(deleteAccomodationReviewReplyQuery,
				Void.class);

		accomodationReviewReplyDeleted.executeUpdate();
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
