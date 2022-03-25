package com.hosting.rest.api.services.Accomodation;

import com.hosting.rest.api.models.Accomodation.AccomodationLocationModel;
import com.hosting.rest.api.models.Accomodation.AccomodationModel;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author Francisco Coya · https://github.com/FranciscoCoya
 * @version v1.0.0
 * @description
 **/
public interface IAccomodationService{

    AccomodationModel addNewAccomodation(AccomodationModel accomodationModel);

    List<AccomodationModel> getAllAccomodations();

    AccomodationModel getAccomodationById(String regNumber);

    @Query("SELECT * FROM ACCOMODATION A INNER JOIN ACCOMODATION_LOCATION AL ON (A.ID_ACC_LOCATION = AL.ID) WHERE A.REG_NUM = ?1")
    List<AccomodationModel> getAccomodationsByCity(String cityToSearch);

    void removeAccomodationById(String regNumber);
}
