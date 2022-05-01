package com.hosting.rest.api.services.Accomodation.SavedAccomodation;

import java.util.List;

import com.hosting.rest.api.models.Accomodation.SavedAccomodation.SavedAccomodationModel;

public interface ISavedAccomodationService {

	public SavedAccomodationModel addNewSavedAccomodation(final SavedAccomodationModel accomodationToSave);
	
	public SavedAccomodationModel getSavedAccomodationById(final Integer savedAccomodationId);
	
	public void deleteSavedAccomodation(final Integer savedAccomodationId);
	
	public List<SavedAccomodationModel> findAllSavedAccomodationsByUserId(final Integer userId);
}
