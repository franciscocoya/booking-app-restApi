package com.hosting.rest.api.services.Accomodation;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hosting.rest.api.exceptions.Accomodation.AccomodationNotFoundException;
import com.hosting.rest.api.models.Accomodation.AccomodationModel;
import com.hosting.rest.api.repositories.Accomodation.IAccomodationRepository;

/**
 * @author Francisco Coya · https://github.com/FranciscoCoya
 * @version v1.0.0
 * @description Implementa los métodos de los servicios de un alojamiento.
 **/
@Service
public class AccomodationServiceImpl implements IAccomodationService {

	@PersistenceContext
	private EntityManager em;
	
	@Autowired
	private IAccomodationRepository accomodationRepo;

	@Override
	public AccomodationModel addNewAccomodation(final AccomodationModel accomodationModel) {
		return accomodationModel != null ? accomodationRepo.save(accomodationModel) : null;
	}

	@Override
	public List<AccomodationModel> listAllAccomodations() {
		return accomodationRepo.findAll();
	}

	@Override
	public AccomodationModel getAccomodationById(final String regNumber) {
		return accomodationRepo.findById(regNumber).orElseThrow(() -> new AccomodationNotFoundException(regNumber));
	}

	@Override
	public void removeAccomodationById(final String regNumber) {
		accomodationRepo.deleteById(regNumber);
	}
	

	@Override
	public List<AccomodationModel> listAccomodationsByCity(final String cityToSearch) {

		String listAccomodationsByCityQuery = "select ac from AccomodationModel ac inner join ac.idAccomodationLocation al where al.city = :city";

		TypedQuery<AccomodationModel> accomodations = em.createQuery(listAccomodationsByCityQuery, AccomodationModel.class);

		accomodations.setParameter("city", cityToSearch);

		return accomodations.getResultList();
		
//		return accomodationRepo.findByCity(cityToSearch);
	}

}
