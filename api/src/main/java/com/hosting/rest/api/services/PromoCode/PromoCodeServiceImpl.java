package com.hosting.rest.api.services.PromoCode;

import static com.hosting.rest.api.Utils.AppUtils.isBigDecimalValid;
import static com.hosting.rest.api.Utils.AppUtils.isIntegerValidAndPositive;
import static com.hosting.rest.api.Utils.AppUtils.isNotNull;
import static com.hosting.rest.api.Utils.AppUtils.isStringNotBlank;
import static com.hosting.rest.api.Utils.ServiceParamValidator.validateParam;
import static com.hosting.rest.api.Utils.ServiceParamValidator.validateParamNotFound;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hosting.rest.api.models.PromoCode.PromoCodeModel;
import com.hosting.rest.api.repositories.Accomodation.IAccomodationRepository;
import com.hosting.rest.api.repositories.PromoCode.IPromoCodeRepository;
import com.hosting.rest.api.repositories.User.IUserRepository;

/**
 * 
 * @author Francisco Coya
 * @version v1.0.3
 * @apiNote Servicio que gestiona los códigos promocionales de los alojamientos.
 *
 */
@Service
public class PromoCodeServiceImpl implements IPromoCodeService {

	@PersistenceContext
	private EntityManager em;

	@Autowired
	private IPromoCodeRepository promoCodeRepo;

	@Autowired
	private IAccomodationRepository accomodationRepo;

	@Autowired
	private IUserRepository userRepo;

	/**
	 * Creación de un código promocional con los datos del modelo pasado como
	 * parámetro.
	 * 
	 * @param promoCodeModel
	 */
	@Override
	public PromoCodeModel addNewPromoCode(final PromoCodeModel promoCodeModel) {
		// Validar código promocional pasado como parámetro
		validateParam(isNotNull(promoCodeModel),
				"Alguno de los valores del código promocional introducido no es válido.");

		// Comprobar si existe el código promocional a añadir
		validateParamNotFound(!promoCodeRepo.existsById(promoCodeModel.getSerial_num()),
				"El código promocional con id " + promoCodeModel.getSerial_num() + " ya existe.");

		return promoCodeRepo.save(promoCodeModel);
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
		// Validar id del código promocional
		validateParam(isStringNotBlank(promoCodeId), "El id del código promocional está vacío o no es válido.");

		// Validar porcentaje descuento código
		validateParam(isBigDecimalValid(newPromoCodeAmountPercentage, true),
				"El porcentaje de descuento introducido no es válido.");

		PromoCodeModel originalPromoCode = promoCodeRepo.findById(promoCodeId).get();

		// Comprobar si existe el código promocional
		validateParamNotFound(promoCodeRepo.existsById(promoCodeId),
				"El código promocional con id [ " + promoCodeId + " ] no existe");

		// Actualizar el porcentaje de descuento del código promocional.
		originalPromoCode.setAmountPercentange(newPromoCodeAmountPercentage);

		return promoCodeRepo.save(originalPromoCode);
	}

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
		// Validar id del código promocional
		validateParam(isStringNotBlank(promoCodeId),
				"El código promocional con id " + promoCodeId + " a añadir está vacío.");

		return promoCodeRepo.findById(promoCodeId).get();
	}

	/**
	 * Borrado de un código promocional por su id <code>promoCodeId</code>
	 * 
	 * @param promoCodeId
	 */
	@Override
	public void removePromoCodeById(final String promoCodeId) {
		// Validar el id del código promocional
		validateParam(isStringNotBlank(promoCodeId),
				"El código promocional con id " + promoCodeId + " a eliminar está vacío.");

		promoCodeRepo.deleteById(promoCodeId);
	}

	/**
	 * Listado de los códigos promocionales creados por el usuario
	 * <code>userId</code> pasado como parámetro.
	 * 
	 * @param userId
	 * 
	 * @throws NumberFormatException Si el id de usuario no es un número.
	 */
	@Override
	public List<PromoCodeModel> findByUser(final Integer userId) throws NumberFormatException {
		// Validar el id del usuario
		validateParam(isIntegerValidAndPositive(userId), "El id del usuario " + userId + " no es válido.");

		// Comprobar si existe el usuario
		validateParamNotFound(userRepo.existsById(userId), "No existe un usuario con id " + userId);

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
		// Validar número de registro del alojamiento
		validateParam(isStringNotBlank(accomodationRegNumber),
				"El número de registro del alojamiento " + accomodationRegNumber + " a a buscar está vacío.");

		// Comprobar que existe un alohamiento con el número de registro pasado como
		// parámetro.
		validateParamNotFound(accomodationRepo.existsById(accomodationRegNumber),
				"No existe ningún alojamiento registrado con el número " + accomodationRegNumber
						+ " en la aplicación.");

		String findPromoCodesByUserIdQuery = "SELECT pc FROM PromoCodeModel pc INNER JOIN pc.idAcc pa"
				+ " WHERE pa.registerNumber = :regNum";

		TypedQuery<PromoCodeModel> promoCodes = em.createQuery(findPromoCodesByUserIdQuery, PromoCodeModel.class);

		promoCodes.setParameter("regNum", accomodationRegNumber);

		return promoCodes.getResultList();
	}

	@Override
	public PromoCodeModel checkPromoCodeValid(final String regNumber, final String promoCodeValue) {
		PromoCodeModel existsCode = null;
		
		// Validar número de registro del alojamiento
		validateParam(isStringNotBlank(regNumber),
				"El número de registro del alojamiento " + regNumber + " a a buscar está vacío.");

		// Comprobar que existe un alohamiento con el número de registro pasado como
		// parámetro.
		validateParamNotFound(accomodationRepo.existsById(regNumber),
				"No existe ningún alojamiento registrado con el número " + regNumber + " en la aplicación.");

		validateParam(isStringNotBlank(promoCodeValue), "El código promocional no es válido.");

		String checkPromoCodeValidQuery = "SELECT pcm FROM PromoCodeAccomodationModel pcam"
				+ ", PromoCodeModel pcm "
				+ "INNER JOIN pcam.promoCodeAccomodationId pcaid" + " WHERE pcaid.idAccomodation = :regNumber "
				+ "AND pcm.serial_num = :promoCodeValue ";

		TypedQuery<PromoCodeModel> promoCode = em.createQuery(checkPromoCodeValidQuery, PromoCodeModel.class)
				.setParameter("regNumber", regNumber).setParameter("promoCodeValue", promoCodeValue);
		
		try {
			existsCode = promoCode.getSingleResult();
			
		} catch (NoResultException nre) {
			existsCode = null;
		}

		return existsCode;
	}
}
