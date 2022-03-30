package com.hosting.rest.api.services.Accomodation.SavedAccomodation;

import static com.hosting.rest.api.Utils.AppUtils.isIntegerValidAndPositive;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hosting.rest.api.models.Accomodation.SavedAccomodation.SavedAccomodationModel;
import com.hosting.rest.api.repositories.Accomodation.SavedAccomodation.ISavedAccomodationRepository;

@Service
public class SavedAccomodationServiceImpl implements ISavedAccomodationService {

	@PersistenceContext
	private EntityManager em;

	@Autowired
	private ISavedAccomodationRepository savedAccomodationRepo;

	@Override
	public SavedAccomodationModel addNewSavedAccomodation(final SavedAccomodationModel accomodationToSave) {
		return savedAccomodationRepo.save(accomodationToSave);
	}

	@Override
	public SavedAccomodationModel getSavedAccomodationById(final Integer savedAccomodationId) {
		return savedAccomodationRepo.findById(savedAccomodationId).get();
	}

	@Override
	public void deleteSavedAccomodation(final Integer savedAccomodationId) {
		savedAccomodationRepo.deleteById(savedAccomodationId);
	}

	@Override
	public List<SavedAccomodationModel> findAllSavedAccomodationsByUserId(final Integer userId) {

		if (!isIntegerValidAndPositive(userId)) {
			return null;
		}

		String findAllSavedAccomodationsByUserIdQuery = "SELECT sam "
				+ "FROM SavedAccomodationModel sam INNER JOIN sam.idUser u " + "WHERE u.id = :userId";

		TypedQuery<SavedAccomodationModel> savedAccomodations = em.createQuery(findAllSavedAccomodationsByUserIdQuery,
				SavedAccomodationModel.class);

		savedAccomodations.setParameter("userId", userId);

		return savedAccomodations.getResultList();
	}

}
