package com.hosting.rest.api.services.Accomodation;

import com.hosting.rest.api.models.Accomodation.AccomodationModel;

import java.util.List;

/**
 * @author Francisco Coya · https://github.com/FranciscoCoya
 * @version v1.0.0
 * @description
 **/
public interface IAccomodationService{

    void addNewAccomodation(AccomodationModel accomodationModel);

    List<AccomodationModel> getAllAccomodations();

    AccomodationModel getAccomodationById(String regNumber);

    void removeAccomodationById(String regNumber);

}
