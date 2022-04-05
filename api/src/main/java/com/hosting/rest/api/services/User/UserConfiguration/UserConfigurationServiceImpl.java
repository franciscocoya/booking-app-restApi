package com.hosting.rest.api.services.User.UserConfiguration;

import static com.hosting.rest.api.Utils.AppUtils.isIntegerValidAndPositive;
import static com.hosting.rest.api.Utils.AppUtils.isNotNull;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hosting.rest.api.exceptions.IllegalArguments.IllegalArgumentsCustomException;
import com.hosting.rest.api.exceptions.NotFound.NotFoundCustomException;
import com.hosting.rest.api.models.Currency.CurrencyModel;
import com.hosting.rest.api.models.Language.LanguageModel;
import com.hosting.rest.api.models.User.UserConfiguration.UserConfigurationModel;
import com.hosting.rest.api.repositories.User.UserConfiguration.IUserConfigurationRepository;

@Service
public class UserConfigurationServiceImpl implements IUserConfigurationService {

//	private static final Logger logger = LogManager.getLogger(UserConfigurationServiceImpl.class);

	@PersistenceContext
	private EntityManager em;

	@Autowired
	private IUserConfigurationRepository userConfigurationRepo;

	@Override
	public UserConfigurationModel addNewUserConfiguration(final UserConfigurationModel newUserConfigurationModel) {
		if (!isNotNull(newUserConfigurationModel)) {
			throw new IllegalArgumentsCustomException(
					"Alguno de los valores de la configuración del usuario a añadir no es válido.");
		}

		return userConfigurationRepo.save(newUserConfigurationModel);
	}

	@Transactional
	@Override
	public UserConfigurationModel updateUserConfiguration(final Integer userId, final LanguageModel newLanguage,
			final CurrencyModel newCurrency) {

		if (!isIntegerValidAndPositive(userId)) {
			throw new IllegalArgumentsCustomException("El id del usuario [ " + userId + " ] no es válido.");
		}

		if (!isNotNull(newLanguage)) {
			throw new IllegalArgumentsCustomException(
					"Alguno de los valores de la configuración del lenguaje no es válido.");
		}

		if (!isNotNull(newCurrency)) {
			throw new IllegalArgumentsCustomException(
					"Alguno de los valores de la configuración de la divisa no es válido.");
		}

		// Comprobar si existe una configuación para el usuario
		UserConfigurationModel originalUserConfiguration = findByUserId(userId);

		if (originalUserConfiguration == null) {
			throw new NotFoundCustomException("La configuración del usuario a actualizar no existe.");
		}

		originalUserConfiguration.setIdCurrency(newCurrency);
		originalUserConfiguration.setIdLanguage(newLanguage);

		return userConfigurationRepo.saveAndFlush(originalUserConfiguration);
	}

	@Override
	public UserConfigurationModel findById(final Integer userConfigurationId) {
		if (!isIntegerValidAndPositive(userConfigurationId)) {
			throw new IllegalArgumentsCustomException(
					"El id de la configuración del usuario [ " + userConfigurationId + " ] no es válido.");
		}

		return userConfigurationRepo.findById(userConfigurationId).get();
	}

	@Override
	public UserConfigurationModel findByUserId(final Integer userId) {
		if (!isIntegerValidAndPositive(userId)) {
			throw new IllegalArgumentsCustomException("El id del usuario [ " + userId + " ] no es válido.");
		}

		String findByUserIdQuery = "SELECT ucm " + "FROM UserModel um INNER JOIN um.idUserConfiguration ucm "
				+ "WHERE um.id = :userId";

		TypedQuery<UserConfigurationModel> userConfig = em.createQuery(findByUserIdQuery, UserConfigurationModel.class);
		
		userConfig.setParameter("userId", userId);
		
		// TODO: Si el usuario no tiene configuración, se muestra un 500 - CORREGIR

		return userConfig.getSingleResult();
	}

	@Transactional
	@Override
	public void deleteUserConfiguration(final Integer userConfigurationId) {
		if (!isIntegerValidAndPositive(userConfigurationId)) {
			throw new IllegalArgumentsCustomException(
					"El id de la configuración del usuario [ " + userConfigurationId + " ] no es válido.");
		}

		userConfigurationRepo.deleteById(userConfigurationId);
	}

}
