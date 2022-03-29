package com.hosting.rest.api.repositories.Accomodation.AccomodationService;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hosting.rest.api.models.Accomodation.AccomodationService.AccomodationAccServiceId;
import com.hosting.rest.api.models.Accomodation.AccomodationService.AccomodationServiceModel;

@Repository
public interface IAccomodationServiceRepository
		extends JpaRepository<AccomodationServiceModel, AccomodationAccServiceId> {

	/**
	 * Listado de todos los servicios disponibles que ofrece un alojamiento.
	 * 
	 * @param accomodationId Número de registro del alojamiento.
	 * @return
	 */
	@Query("select asm from AccomodationServiceModel asm, AccomodationAccServiceModel accs inner join accs.accomodationAccServiceId acs "
			+ "where acs.idAccomodation = :regNumber and asm.id = acs.idAccomodationService")
	public List<AccomodationServiceModel> findByAccomodationId(@Param(value = "regNumber") String accomodationId);
}
