package com.hosting.rest.api.services.Accomodation.AccomodationReview;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hosting.rest.api.models.Accomodation.AccomodationReviewModel;
import com.hosting.rest.api.repositories.Accomodation.AccomodationReview.IAccomodationReviewRepository;

@Service
public class AccomodationReviewServiceImpl implements IAccomodationReviewService {

	@Autowired
	private IAccomodationReviewRepository accomodationReviewRepo;

	@Override
	public AccomodationReviewModel addNewAccomodationReview(AccomodationReviewModel accomodationToAdd) {
		return accomodationReviewRepo.save(accomodationToAdd);
	}

	@Override
	public AccomodationReviewModel udpateAccomodationReview(AccomodationReviewModel accomodationToUpdate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteAccomodationReviewById(Integer accomodationReviewId) {
		accomodationReviewRepo.deleteById(accomodationReviewId);
	}

	@Override
	public List<AccomodationReviewModel> findAllAccomodationReviews(String regNumber) {
		return accomodationReviewRepo.findByAccomodationId(regNumber);
	}

	@Override
	public AccomodationReviewModel getAccomodationReviewById(Integer accomodationReviewId) {
		return accomodationReviewRepo.findById(accomodationReviewId).get();
	}

}
