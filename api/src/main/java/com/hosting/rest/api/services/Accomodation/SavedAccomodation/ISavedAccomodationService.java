package com.hosting.rest.api.services.Accomodation.SavedAccomodation;

import java.util.List;

import com.hosting.rest.api.models.Accomodation.SavedAccomodation.SavedAccomodationModel;

public interface ISavedAccomodationService {

	SavedAccomodationModel addNewSavedAccomodation(final SavedAccomodationModel accomodationToSave);
	
	SavedAccomodationModel getSavedAccomodationById(final Integer savedAccomodationId);
	
	void deleteSavedAccomodation(final Integer savedAccomodationId);
	
	List<SavedAccomodationModel> findAllSavedAccomodationsByUserId(final Integer userId);
}
