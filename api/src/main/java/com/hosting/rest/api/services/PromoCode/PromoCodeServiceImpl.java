package com.hosting.rest.api.services.PromoCode;

import static com.hosting.rest.api.Utils.AppUtils.isBigDecimalValid;
import static com.hosting.rest.api.Utils.AppUtils.isIntegerValidAndPositive;
import static com.hosting.rest.api.Utils.AppUtils.isNotNull;
import static com.hosting.rest.api.Utils.AppUtils.isStringNotBlank;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hosting.rest.api.exceptions.IllegalArguments.IllegalArgumentsCustomException;
import com.hosting.rest.api.exceptions.NotFound.NotFoundCustomException;
import com.hosting.rest.api.models.PromoCode.PromoCodeModel;
import com.hosting.rest.api.repositories.Accomodation.IAccomodationRepository;
import com.hosting.rest.api.repositories.PromoCode.IPromoCodeRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author Francisco Coya
 * @version v1.0.2
 * @apiNote Servicio que gestiona los códigos promocionales de los alojamientos.
 *
 */
@Service
@Slf4j
public class PromoCodeServiceImpl implements IPromoCodeService {

	@PersistenceContext
	private EntityManager em;

	@Autowired
	private IPromoCodeRepository promoCodeRepo;

	@Autowired
	private IAccomodationRepository accomodationRepo;

	/**
	 * Listado de todos los códigos promocionales creados en la aplicación.
	 */
	@Override
	public List<PromoCodeModel> findAllPromoCodes() {
		return promoCodeRepo.findAll();
	}

	/**
	 * Obtención de un código promocional con el id <code>promoCodeId</code> pasado
	 * como parámetro.
	 * 
	 * @param promoCodeId
	 */
	@Override
	public PromoCodeModel getPromoCodeById(final String promoCodeId) {

		if (!isStringNotBlank(promoCodeId)) {
			log.error("El código promocional con id " + promoCodeId + " a añadir está vacío.");
			throw new IllegalArgumentsCustomException(
					"El código promocional con id " + promoCodeId + " a añadir está vacío.");
		}

		return promoCodeRepo.findById(promoCodeId).get();
	}

	/**
	 * Creación de un código promocional con los datos del modelo pasado como
	 * parámetro.
	 * 
	 * @param promoCodeModel
	 */
	@Override
	public PromoCodeModel addNewPromoCode(final PromoCodeModel promoCodeModel) {
		if (!isNotNull(promoCodeModel)) {
			log.error("Alguno de los valores del código promocional introducido no es válido.");
			throw new IllegalArgumentsCustomException(
					"Alguno de los valores del código promocional introducido no es válido.");
		}

		// Comprobar si existe el código promocional a añadir
		if (promoCodeRepo.existsById(promoCodeModel.getSerial_num())) {
			log.error("El código promocional con id " + promoCodeModel.getSerial_num() + " ya existe.");
			throw new IllegalArgumentsCustomException(
					"El código promocional con id " + promoCodeModel.getSerial_num() + " ya existe.");
		}

		return promoCodeRepo.save(promoCodeModel);
	}

	/**
	 * Borrado de un código promocional por su id <code>promoCodeId</code>
	 * 
	 * @param promoCodeId
	 */
	@Override
	public void removePromoCodeById(final String promoCodeId) {
		if (!isStringNotBlank(promoCodeId)) {
			log.error("El código promocional con id " + promoCodeId + " a eliminar está vacío.");
			throw new IllegalArgumentsCustomException(
					"El código promocional con id " + promoCodeId + " a eliminar está vacío.");
		}

		promoCodeRepo.deleteById(promoCodeId);
	}

	/**
	 * Listado de los códigos promocionales creados por el usuario
	 * <code>userId</code> pasado como parámetro.
	 * 
	 * @param userId
	 */
	@Override
	public List<PromoCodeModel> findByUser(final Integer userId) {

		if (!isIntegerValidAndPositive(userId)) {
			log.error("El id del usuario " + userId + " no es válido.");
			throw new IllegalArgumentsCustomException("El id del usuario " + userId + " no es válido.");
		}

		String findPromoCodesByUserIdQuery = "SELECT pc FROM PromoCodeModel pc INNER JOIN pc.idUser pu"
				+ " WHERE pu.id = :userId";

		TypedQuery<PromoCodeModel> promoCodes = em.createQuery(findPromoCodesByUserIdQuery, PromoCodeModel.class);

		promoCodes.setParameter("userId", userId);

		return promoCodes.getResultList();
	}

	/**
	 * Listado de todos los códigos promocionales disponibles en un alojamiento
	 * <code>accomodationRegNumber</code> <code>userId</code>.
	 * 
	 * @param accomodationRegNumber
	 * 
	 * @return
	 */
	@Override
	public List<PromoCodeModel> findByAccomodation(final String accomodationRegNumber) {

		if (!isStringNotBlank(accomodationRegNumber)) {
			log.error("El número de registro del alojamiento " + accomodationRegNumber + " a a buscar está vacío.");
			throw new IllegalArgumentsCustomException(
					"El número de registro del alojamiento " + accomodationRegNumber + " a a buscar está vacío.");
		}

		// Comprobar que existe un alohamiento con el número de registro pasado como
		// parámetro.
		if (!accomodationRepo.existsById(accomodationRegNumber)) {
			log.error("No existe ningún alojamiento registrado con el número " + accomodationRegNumber
					+ " en la aplicación.");
			throw new NotFoundCustomException("No existe ningún alojamiento registrado con el número "
					+ accomodationRegNumber + " en la aplicación.");
		}

		String findPromoCodesByUserIdQuery = "SELECT pc FROM PromoCodeModel pc INNER JOIN pc.idAcc pa"
				+ " WHERE pa.registerNumber = :regNum";

		TypedQuery<PromoCodeModel> promoCodes = em.createQuery(findPromoCodesByUserIdQuery, PromoCodeModel.class);

		promoCodes.setParameter("regNum", accomodationRegNumber);

		return promoCodes.getResultList();
	}

	/**
	 * Actualiza los datos de un código promocional con id <code>promoCodeId</code>
	 * con el contenido de <code>promoCodeToUpdate</code>.
	 * 
	 * @param promoCodeId
	 * @param promoCodeToUpdate
	 * 
	 * @return
	 */
	@Override
	public PromoCodeModel updatePromoCode(final String promoCodeId, final BigDecimal newPromoCodeAmountPercentage) {

		if (!isStringNotBlank(promoCodeId)) {
			log.error("El id del código promocional está vacío o no es válido.");
			throw new IllegalArgumentsCustomException("El id del código promocional está vacío o no es válido.");
		}

		if (!isBigDecimalValid(newPromoCodeAmountPercentage, true)) {
			log.error("El porcentaje de descuento introducido no es válido.");
			throw new IllegalArgumentsCustomException("El porcentaje de descuento introducido no es válido.");
		}

		PromoCodeModel originalPromoCode = promoCodeRepo.findById(promoCodeId).get();

		// Comprobar si existe el código promocional
		if (!promoCodeRepo.existsById(promoCodeId)) {
			log.error("El código promocional con id [ " + promoCodeId + " ] no existe");
			throw new NotFoundCustomException("El código promocional con id [ " + promoCodeId + " ] no existe");
		}

		// Actualizar el porcentaje de descuento del código promocional.
		originalPromoCode.setAmountPercentange(newPromoCodeAmountPercentage);

		return promoCodeRepo.save(originalPromoCode);
	}
}
