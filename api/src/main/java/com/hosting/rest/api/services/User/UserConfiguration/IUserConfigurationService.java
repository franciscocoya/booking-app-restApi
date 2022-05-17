package com.hosting.rest.api.services.User.UserConfiguration;

import java.util.List;

import com.hosting.rest.api.models.Currency.CurrencyModel;
import com.hosting.rest.api.models.Language.LanguageModel;
import com.hosting.rest.api.models.User.UserConfiguration.UserConfigurationModel;

public interface IUserConfigurationService {

	public UserConfigurationModel addNewUserConfiguration(final UserConfigurationModel newUserConfigurationModel);

	public UserConfigurationModel updateUserConfiguration(final Integer userId,
			final LanguageModel newLanguage, final CurrencyModel newCurrency);

	public UserConfigurationModel findById(final Integer userConfigurationId);

	public UserConfigurationModel findByUserId(final Integer userId);

	public void deleteUserConfiguration(final Integer userConfigurationId);
	
	public List<UserConfigurationModel> findAll();
	
	public List<CurrencyModel> findAllCurrencies();
	
	public List<LanguageModel> findAllLanguages();
}
