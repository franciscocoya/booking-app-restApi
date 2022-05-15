package com.hosting.rest.api.services.Accomodation.SavedAccomodation;

import java.util.List;

import com.hosting.rest.api.models.Accomodation.SavedAccomodation.SavedAccomodationModel;

public interface ISavedAccomodationService {

	public SavedAccomodationModel addNewSavedAccomodation(final SavedAccomodationModel accomodationToSave);
	
	public SavedAccomodationModel addNewSavedAccomodationByRegNumber(final String regNumber, final Integer userId);
	
	public SavedAccomodationModel getSavedAccomodationById(final Integer savedAccomodationId);
	
	public SavedAccomodationModel getSavedAccomodationByRegNumberAndUserId(final String regNumber, final Integer userId);
	
	public void deleteSavedAccomodation(final Integer savedAccomodationId);
	
	public void deleteSavedAccomodationByRegNumberAndUserId(final String regNumber, final Integer userId);
	
	public List<SavedAccomodationModel> findAllSavedAccomodationsByUserId(final Integer userId);
}
