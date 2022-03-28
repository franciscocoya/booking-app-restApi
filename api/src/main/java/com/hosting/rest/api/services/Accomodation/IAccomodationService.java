package com.hosting.rest.api.services.Accomodation;

import java.util.List;

import com.hosting.rest.api.models.Accomodation.AccomodationModel;

/**
 * @author Francisco Coya · https://github.com/FranciscoCoya
 * @version v1.0.0
 * @description
 **/
public interface IAccomodationService {

	AccomodationModel addNewAccomodation(AccomodationModel accomodationModel);

	List<AccomodationModel> listAllAccomodations();

	AccomodationModel getAccomodationById(String regNumber);

	void removeAccomodationById(String regNumber);

	List<AccomodationModel> listAccomodationsByCity(String cityToSearch);
}
