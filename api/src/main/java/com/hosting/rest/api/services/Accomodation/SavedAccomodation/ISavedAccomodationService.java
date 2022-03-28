package com.hosting.rest.api.services.Accomodation.SavedAccomodation;

import java.util.List;

import com.hosting.rest.api.models.Accomodation.SavedAccomodation.SavedAccomodationModel;

public interface ISavedAccomodationService {

	SavedAccomodationModel addNewSavedAccomodation(SavedAccomodationModel accomodationToSave);
	
	SavedAccomodationModel getSavedAccomodationById(Integer savedAccomodationId);
	
	void deleteSavedAccomodation(Integer savedAccomodationId);
	
	List<SavedAccomodationModel> listAllSavedAccomodationOfUser(Integer userId);
}
