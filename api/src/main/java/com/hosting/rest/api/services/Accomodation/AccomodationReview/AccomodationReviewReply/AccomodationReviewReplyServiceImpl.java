package com.hosting.rest.api.services.Accomodation.AccomodationReview.AccomodationReviewReply;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
		// TODO Auto-generated method stub
		return accomodationReviewReplyRepo.save(accomodationReviewReplyToAdd);
	}

	@Override
	public void deleteAccomodationReviewReplyById(final Integer accomodationReviewReplyId) {
		// TODO Auto-generated method stub
		//accomodationReviewReplyRepo.deleteById(accomodationReviewReplyId);
	}

	@Override
	public AccomodationReviewReplyModel findByReviewId(final Integer accomodationReviewId) {
		String findByReviewIdQuery = "SELECT arrm "
				+ "FROM AccomodationReviewReplyModel arrm INNER JOIN arrm.accomodationReviewReplyId.idAccomodationReview arm"
				+ "WHERE arn.id = :accomodationReviewId";

		TypedQuery<AccomodationReviewReplyModel> accomodationReviewReply = em.createQuery(findByReviewIdQuery,
				AccomodationReviewReplyModel.class);

		accomodationReviewReply.setParameter("accomodationReviewId", accomodationReviewId);

		return accomodationReviewReply.getSingleResult();
	}

}
