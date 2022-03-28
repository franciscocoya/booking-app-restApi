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
	public SavedAccomodationModel addNewSavedAccomodation(SavedAccomodationModel accomodationToSave) {
		return savedAccomodationRepo.save(accomodationToSave);
	}

	@Override
	public SavedAccomodationModel getSavedAccomodationById(Integer savedAccomodationId) {
		return savedAccomodationRepo.findById(savedAccomodationId).get();
	}

	@Override
	public void deleteSavedAccomodation(Integer savedAccomodationId) {
		savedAccomodationRepo.deleteById(savedAccomodationId);
	}

	@Override
	public List<SavedAccomodationModel> listAllSavedAccomodationOfUser(Integer userId) {
		return null;
	}

}
