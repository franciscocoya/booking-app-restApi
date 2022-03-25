package com.hosting.rest.api.services.Accomodation;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.hosting.rest.api.models.Accomodation.AccomodationModel;

/**
 * @author Francisco Coya · https://github.com/FranciscoCoya
 * @version v1.0.0
 * @description
 **/
public interface IAccomodationService{

    AccomodationModel addNewAccomodation(AccomodationModel accomodationModel);

    List<AccomodationModel> listAllAccomodations();

    AccomodationModel getAccomodationById(String regNumber);

    @Query("SELECT * FROM ACCOMODATION A INNER JOIN ACCOMODATION_LOCATION AL ON (A.ID_ACC_LOCATION = AL.ID) WHERE A.REG_NUM = ?1")
    List<AccomodationModel> listAccomodationsByCity(String cityToSearch);

    void removeAccomodationById(String regNumber);
}
