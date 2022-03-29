package com.hosting.rest.api.services.Accomodation;

import java.util.List;

import com.hosting.rest.api.models.Accomodation.AccomodationModel;

/**
 * @author Francisco Coya · https://github.com/FranciscoCoya
 * @version v1.0.0
 * @description
 **/
public interface IAccomodationService {

	public static final int EARTH_RADIUS = 6371; // En Km

	/**
	 * Fórmula para realizar el cálculo de un radio a partir de unas coordenadas de
	 * origen.
	 * {@link https://gautamsuraj.medium.com/haversine-formula-for-spring-data-jpa-db6a53516dc9}
	 */
	public static final String HAVERSINE_FORMULA = "(" + EARTH_RADIUS
			+ " * acos(cos(radians(:latitude)) * cos(radians(s.latitude)) *"
			+ " cos(radians(s.longitude) - radians(:longitude)) + sin(radians(:latitude)) * sin(radians(s.latitude))))";

	public AccomodationModel addNewAccomodation(final AccomodationModel accomodationModel);

	public List<AccomodationModel> listAllAccomodations();

	public AccomodationModel getAccomodationById(final String regNumber);

	public void removeAccomodationById(final String regNumber);

	public List<AccomodationModel> listAccomodationsByCity(final String cityToSearch);

	public List<AccomodationModel> findByRadiusFromCoordinates(final double lat, final double lng, final double distanceRadius);
}
