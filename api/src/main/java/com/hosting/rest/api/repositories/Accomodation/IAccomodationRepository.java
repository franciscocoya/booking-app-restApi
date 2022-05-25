package com.hosting.rest.api.repositories.Accomodation;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.hosting.rest.api.models.Accomodation.AccomodationModel;

/**
 * @author Francisco Coya · https://github.com/FranciscoCoya
 * @version v1.0.2
 * @description
 **/
public interface IAccomodationRepository
		extends JpaRepository<AccomodationModel, String>, PagingAndSortingRepository<AccomodationModel, String>, JpaSpecificationExecutor<AccomodationModel>  {

	@Query("SELECT DISTINCT aml.city FROM AccomodationLocationModel aml WHERE aml.city LIKE %:match% ")
	List<String> findBySearchCriteria(@Param("match") final String searchCriteria);
	
}
