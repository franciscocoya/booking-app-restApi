package com.hosting.rest.api.repositories.Accomodation;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.hosting.rest.api.models.Accomodation.AccomodationModel;

/**
 * @author Francisco Coya · https://github.com/FranciscoCoya
 * @version v1.0.0
 * @description
 **/
public interface IAccomodationRepository
		extends JpaRepository<AccomodationModel, String>, PagingAndSortingRepository<AccomodationModel, String> {

	String HAVERSINE_FORMULA = "(6371 * acos(cos(radians(:latitude)) * cos(radians(s.latitude)) *" +
	        " cos(radians(s.longitude) - radians(:longitude)) + sin(radians(:latitude)) * sin(radians(s.latitude))))";
	
	
	/**
	 * Listado de los alojamientos de una ciudad.
	 * 
	 * @param cityToSearch
	 * @return
	 */
//	@Query("select ac from AccomodationModel ac inner join ac.idAccomodationLocation al where al.city = :city")
//	List<AccomodationModel> findByCity(@Param(value = "city") String cityToSearch);
}
