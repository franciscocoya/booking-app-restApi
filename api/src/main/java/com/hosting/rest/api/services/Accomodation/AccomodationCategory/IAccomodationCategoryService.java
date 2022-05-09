package com.hosting.rest.api.services.Accomodation.AccomodationCategory;

import java.util.List;

import com.hosting.rest.api.models.Accomodation.AccomodationCategoryModel;

public interface IAccomodationCategoryService {

	public AccomodationCategoryModel addNewAccomodationCategory(final AccomodationCategoryModel accomodationToAdd);
	
	public void deleteAccomodationCategoryById(final Integer accomodationCategoryId);
	
	public List<AccomodationCategoryModel> findAllAccomodationCategories();
}
