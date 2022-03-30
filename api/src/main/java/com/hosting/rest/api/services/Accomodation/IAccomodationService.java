package com.hosting.rest.api.services.Accomodation;

import java.math.BigDecimal;
import java.util.List;

import com.hosting.rest.api.models.Accomodation.AccomodationModel;

/**
 * @author Francisco Coya · https://github.com/FranciscoCoya
 * @version v1.0.0
 * @description
 **/
public interface IAccomodationService {

	
	public static final int ACCOMODATION_LIMIT_RESULTS = 100;
	
	public static final int EARTH_RADIUS = 6371; // En Km

	/**
	 * Fórmula para realizar el cálculo de un radio a partir de unas coordenadas de
	 * origen.
	 * {@link https://gautamsuraj.medium.com/haversine-formula-for-spring-data-jpa-db6a53516dc9}
	 */
	public static final String HAVERSINE_FORMULA = "(" + EARTH_RADIUS
			+ " * acos(cos(radians(:latitude)) * cos(radians(acloc.latitude)) *"
			+ " cos(radians(acloc.longitude) - radians(:longitude)) + sin(radians(:latitude)) * sin(radians(acloc.latitude))))";

	public AccomodationModel addNewAccomodation(final AccomodationModel accomodationModel);

	public List<AccomodationModel> findAllAccomodations();

	public AccomodationModel getAccomodationById(final String regNumber);
	
	public AccomodationModel updateAccomodationById(final String regNumber, final AccomodationModel accomodationToUpdate);

	public String removeAccomodationById(final String regNumber);

	public List<AccomodationModel> findByCity(final String cityToSearch);

	public List<AccomodationModel> findByNearby(final BigDecimal lat, final BigDecimal lng, final double distanceRadius);
}
