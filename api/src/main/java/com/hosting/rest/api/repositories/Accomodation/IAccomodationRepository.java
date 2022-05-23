package com.hosting.rest.api.repositories.Accomodation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.hosting.rest.api.models.Accomodation.AccomodationModel;

/**
 * @author Francisco Coya · https://github.com/FranciscoCoya
 * @version v1.0.2
 * @description
 **/
public interface IAccomodationRepository
		extends JpaRepository<AccomodationModel, String>, PagingAndSortingRepository<AccomodationModel, String>, JpaSpecificationExecutor<AccomodationModel>  {
}
