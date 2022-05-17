package com.hosting.rest.api.services.User.UserConfiguration;

import static com.hosting.rest.api.Utils.AppUtils.isIntegerValidAndPositive;
import static com.hosting.rest.api.Utils.AppUtils.isNotNull;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hosting.rest.api.exceptions.IllegalArguments.IllegalArgumentsCustomException;
import com.hosting.rest.api.exceptions.NotFound.NotFoundCustomException;
import com.hosting.rest.api.models.Currency.CurrencyModel;
import com.hosting.rest.api.models.Language.LanguageModel;
import com.hosting.rest.api.models.User.UserModel;
import com.hosting.rest.api.models.User.UserConfiguration.UserConfigurationModel;
import com.hosting.rest.api.repositories.Currency.IAppCurrencyRepository;
import com.hosting.rest.api.repositories.Language.IAppLanguageRepository;
import com.hosting.rest.api.repositories.User.IUserRepository;
import com.hosting.rest.api.repositories.User.UserConfiguration.IUserConfigurationRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author Francisco Coya
 * @version v1.0.0
 * @apiNote Servicio que gestiona la configuración de un usuario de la
 *          aplicación.
 *
 */
@Slf4j
@Service
public class UserConfigurationServiceImpl implements IUserConfigurationService {

	@PersistenceContext
	private EntityManager em;

	@Autowired
	private IUserConfigurationRepository userConfigurationRepo;

	@Autowired
	private IUserRepository userRepo;

	@Autowired
	private IAppCurrencyRepository currencyRepo;

	@Autowired
	private IAppLanguageRepository languageRepo;

	/**
	 * Añade una nueva configuración de usuario.
	 * 
	 * @param newUserConfigurationToAdd
	 * 
	 * @return
	 */
	@Override
	public UserConfigurationModel addNewUserConfiguration(final UserConfigurationModel newUserConfigurationToAdd) {
		if (!isNotNull(newUserConfigurationToAdd)) {
			log.error("Alguno de los valores de la configuración del usuario a añadir no es válido.");
			throw new IllegalArgumentsCustomException(
					"Alguno de los valores de la configuración del usuario a añadir no es válido.");
		}

		if (userConfigurationRepo.existsById(newUserConfigurationToAdd.getIdUserConfiguration())) {
			log.error("Ya existe una configuración con las mismas características.");
			throw new IllegalArgumentsCustomException("Ya existe una configuración con las mismas características.");
		}

		return newUserConfigurationToAdd != null ? userConfigurationRepo.save(newUserConfigurationToAdd) : null;
	}

	/**
	 * Actualiza los datos de la configuración del usuario <code>userId</code> con
	 * los datos respectivos a lenguaje y divisa.
	 * 
	 * @param userId
	 * @param newLanguage
	 * @param newCurrency
	 */
	@Transactional
	@Override
	public UserConfigurationModel updateUserConfiguration(final Integer userId, final LanguageModel newLanguage,
			final CurrencyModel newCurrency) {

		// TODO: Revisar este método para optimizarlo.

		// Comprobar que el id de usuario es válido.
		if (!isIntegerValidAndPositive(userId)) {
			log.error("El id del usuario [ " + userId + " ] no es válido.");
			throw new IllegalArgumentsCustomException("El id del usuario [ " + userId + " ] no es válido.");
		}

		// Comprobar que el lenguaje no es null.
		if (!isNotNull(newLanguage)) {
			log.error("Alguno de los valores de la configuración del lenguaje no es válido.");
			throw new IllegalArgumentsCustomException(
					"Alguno de los valores de la configuración del lenguaje no es válido.");
		}

		// Comprobar que la divisa no es null
		if (!isNotNull(newCurrency)) {
			log.error("Alguno de los valores de la configuración de la divisa no es válido.");
			throw new IllegalArgumentsCustomException(
					"Alguno de los valores de la configuración de la divisa no es válido.");
		}

		// Antes de buscar la configuración, se comprueba que el usuario exista.
		boolean existsUser = userRepo.existsById(userId);

		if (!existsUser) {
			log.error("El usuario con id [ " + userId + " ] no existe.");
			throw new NotFoundCustomException("El usuario con id [ " + userId + " ] no existe.");
		}
		
		
					UserModel userToUpdateConfig = userRepo.getById(userId);

		// Comprobar si existe una configuación para el usuario
		UserConfigurationModel originalUserConfiguration = findByUserId(userId);
		
		// Si existe la configuración, crear una nueva y asignársela al usuario
		if(userConfigurationRepo.existsById(originalUserConfiguration.getIdUserConfiguration())) {
			UserConfigurationModel newConfig = userConfigurationRepo.save(new UserConfigurationModel(newLanguage, newCurrency));
			// Actualizar configuración en el usuario
			
			userToUpdateConfig.setIdUserConfiguration(newConfig);
			userRepo.save(userToUpdateConfig);
			
			return newConfig;
		}

		originalUserConfiguration.setIdCurrency(newCurrency);
		originalUserConfiguration.setIdLanguage(newLanguage);

		UserConfigurationModel userConfigurationUpdated = userConfigurationRepo.save(originalUserConfiguration);
		
		// Actualizar configuración realizada en el usuario.
		userToUpdateConfig.setIdUserConfiguration(userConfigurationUpdated);
		
		return userConfigurationUpdated;
	}

	/**
	 * @param userConfigurationId
	 * @return Devuelve los datos de la configuración a partir de su id.
	 */
	@Override
	public UserConfigurationModel findById(final Integer userConfigurationId) {
		if (!isIntegerValidAndPositive(userConfigurationId)) {
			log.error("El id de la configuración del usuario [ " + userConfigurationId + " ] no es válido.");
			throw new IllegalArgumentsCustomException(
					"El id de la configuración del usuario [ " + userConfigurationId + " ] no es válido.");
		}

		return userConfigurationRepo.findById(userConfigurationId).get();
	}

	/**
	 * @param userId
	 * 
	 * @return Devuelve los datos de configuración del usuario <code>userId</code>
	 *         pasado como parametro.
	 */
	@Override
	public UserConfigurationModel findByUserId(final Integer userId) {

		UserConfigurationModel configToReturn = null;

		// Comprobar que el id de usuario es válido.
		if (!isIntegerValidAndPositive(userId)) {
			log.error("El id del usuario [ " + userId + " ] no es válido.");
			throw new IllegalArgumentsCustomException("El id del usuario [ " + userId + " ] no es válido.");
		}

		// Antes de buscar la configuración, se comprueba que el usuario exista.
		boolean existsUser = userRepo.existsById(userId);

		if (!existsUser) {
			log.error("El usuario con id [ " + userId + " ] no existe.");
			throw new NotFoundCustomException("El usuario con id [ " + userId + " ] no existe.");
		}

		String findByUserIdQuery = "SELECT ucm " + "FROM UserModel um INNER JOIN um.idUserConfiguration ucm "
				+ "WHERE um.id = :userId";

		TypedQuery<UserConfigurationModel> userConfig = em.createQuery(findByUserIdQuery, UserConfigurationModel.class);

		userConfig.setParameter("userId", userId);

		try {
			configToReturn = userConfig.getSingleResult();

		} catch (NoResultException e) {
			configToReturn = null;
		}

		return configToReturn;
	}

	/**
	 * Elimina la configuración de usuario cuyo id es pasado como parámetro.
	 * 
	 * @param userConfigurationId
	 */
	@Transactional
	@Override
	public void deleteUserConfiguration(final Integer userConfigurationId) {
		if (!isIntegerValidAndPositive(userConfigurationId)) {
			log.error("El id de la configuración del usuario [ " + userConfigurationId + " ] no es válido.");
			throw new IllegalArgumentsCustomException(
					"El id de la configuración del usuario [ " + userConfigurationId + " ] no es válido.");
		}

		userConfigurationRepo.deleteById(userConfigurationId);
	}

	/**
	 * Listado de todas las configuraciones de usuario disponibles.
	 */
	@Override
	public List<UserConfigurationModel> findAll() {
		return userConfigurationRepo.findAll();
	}

	@Override
	public List<CurrencyModel> findAllCurrencies() {
		return currencyRepo.findAll();
	}

	@Override
	public List<LanguageModel> findAllLanguages() {
		return languageRepo.findAll();
	}

}
