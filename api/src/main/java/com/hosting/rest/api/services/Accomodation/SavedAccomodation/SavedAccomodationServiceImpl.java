package com.hosting.rest.api.services.Accomodation.SavedAccomodation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hosting.rest.api.models.Accomodation.SavedAccomodation.SavedAccomodationModel;
import com.hosting.rest.api.repositories.Accomodation.SavedAccomodation.ISavedAccomodationRepository;

@Service
public class SavedAccomodationServiceImpl implements ISavedAccomodationService {

	@Autowired
	private ISavedAccomodationRepository savedAccomodationRepo;
	
	@Override
	public SavedAccomodationModel addNewSavedAccomodation(final SavedAccomodationModel accomodationToSave) {
		return savedAccomodationRepo.save(accomodationToSave);
	}

	@Override
	public SavedAccomodationModel getSavedAccomodationById(final Integer savedAccomodationId) {
		return savedAccomodationRepo.findById(savedAccomodationId).get();
	}

	@Override
	public void deleteSavedAccomodation(final Integer savedAccomodationId) {
		savedAccomodationRepo.deleteById(savedAccomodationId);
	}

	@Override
	public List<SavedAccomodationModel> listAllSavedAccomodationOfUser(final Integer userId) {
		return null;
	}

}
