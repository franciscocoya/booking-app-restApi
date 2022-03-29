package com.hosting.rest.api.services.PromoCode;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hosting.rest.api.models.PromoCode.PromoCodeModel;
import com.hosting.rest.api.repositories.PromoCode.IPromoCodeRepository;

@Service
public class PromoCodeServiceImpl implements IPromoCodeService {

	@PersistenceContext
	private EntityManager em;

	@Autowired
	private IPromoCodeRepository promoCodeRepo;

	@Override
	public List<PromoCodeModel> listAllPromoCodes() {
		return promoCodeRepo.findAll();
	}

	@Override
	public PromoCodeModel getPromoCodeById(final String promoCodeId) {
		return promoCodeRepo.findById(promoCodeId).get();
	}

	@Override
	public List<PromoCodeModel> findAllPromoCodeFromUser(final String userId) {
//		String listAllPromoCodeFromUserQuery = "SELECT * FROM PromoCodeModel PC INNER JOIN UserModel AU ON (PC.ID_USER = AU.ID) WHERE PC.ID_USER = :idUser";
//		
//		TypedQuery<PromoCodeModel> userPromoCodes = getEntityManager().createQuery(listAllPromoCodeFromUserQuery, PromoCodeModel.class);
//		
//		userPromoCodes.setParameter("userId", userId);
//				
//		return userPromoCodes.getResultList();
		return null;
	}

//	private EntityManager getEntityManager() {
//		return entityManager;
//	}

	@Override
	public PromoCodeModel addNewPromoCode(final PromoCodeModel promoCodeModel) {
		return promoCodeRepo.save(promoCodeModel);
	}

	@Override
	public void removePromoCodeById(final String promoCodeId) {
		promoCodeRepo.deleteById(promoCodeId);
	}
}
