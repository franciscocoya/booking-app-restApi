package com.hosting.rest.api.services.PromoCode;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;

import com.hosting.rest.api.models.PromoCode.PromoCodeModel;
import com.hosting.rest.api.repositories.PromoCode.IPromoCodeRepository;

public class PromoCodeServiceImpl implements IPromoCodeService {

	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	private IPromoCodeRepository promoCodeRepo;

	@Override
	public List<PromoCodeModel> listAllPromoCodes() {
		return promoCodeRepo.findAll();
	}

	@Override
	public PromoCodeModel getPromoCodeById(Integer promoCodeId) {
		return promoCodeRepo.getById(promoCodeId);
	}

	@Override
	public List<PromoCodeModel> listAllPromoCodeFromUser(Integer userId) {
		String listAllPromoCodeFromUserQuery = "SELECT * FROM PromoCodeModel PC INNER JOIN UserModel AU ON (PC.ID_USER = AU.ID) WHERE PC.ID_USER = :idUser";
		
		TypedQuery<PromoCodeModel> userPromoCodes = getEntityManager().createQuery(listAllPromoCodeFromUserQuery, PromoCodeModel.class);
		
		userPromoCodes.setParameter("userId", userId);
				
		return userPromoCodes.getResultList();
	}

	private EntityManager getEntityManager() {
		return entityManager;
	}

	@Override
	public PromoCodeModel addNewPromoCode(PromoCodeModel promoCodeModel) {
		return promoCodeRepo.save(promoCodeModel);
	}

	@Override
	public void removePromoCodeById(Integer promoCodeId) {
		promoCodeRepo.deleteById(promoCodeId);
	}
}
