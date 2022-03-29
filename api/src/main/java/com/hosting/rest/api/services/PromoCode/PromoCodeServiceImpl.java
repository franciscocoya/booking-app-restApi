package com.hosting.rest.api.services.PromoCode;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

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
	public List<PromoCodeModel> findAllPromoCodes() {
		return promoCodeRepo.findAll();
	}

	@Override
	public PromoCodeModel getPromoCodeById(final String promoCodeId) {
		return promoCodeRepo.findById(promoCodeId).get();
	}

	@Override
	public PromoCodeModel addNewPromoCode(final PromoCodeModel promoCodeModel) {
		return promoCodeRepo.save(promoCodeModel);
	}

	@Override
	public void removePromoCodeById(final String promoCodeId) {
		promoCodeRepo.deleteById(promoCodeId);
	}

	@Override
	public List<PromoCodeModel> findByUser(final Integer userId) {

		/**
		 * Listado de todos los códigos promocionales creados por un usuario
		 * <code>userId</code>.
		 */
		String findPromoCodesByUserIdQuery = "select pc from PromoCodeModel pc inner join pc.idUser pu"
				+ " where pu.id = :userId";

		TypedQuery<PromoCodeModel> promoCodes = em.createQuery(findPromoCodesByUserIdQuery, PromoCodeModel.class);

		promoCodes.setParameter("userId", userId);

		return promoCodes.getResultList();
	}

	@Override
	public List<PromoCodeModel> findByAccomodation(String accomodationRegNumber) {
		/**
		 * Listado de todos los códigos promocionales disponibles en un alojamiento <code>accomodationRegNumber</code>
		 * <code>userId</code>.
		 */
		String findPromoCodesByUserIdQuery = "select pc from PromoCodeModel pc inner join pc.idAcc pa"
				+ " where pa.registerNumber = :regNum";

		TypedQuery<PromoCodeModel> promoCodes = em.createQuery(findPromoCodesByUserIdQuery, PromoCodeModel.class);

		promoCodes.setParameter("regNum", accomodationRegNumber);

		return promoCodes.getResultList();
	}
}
