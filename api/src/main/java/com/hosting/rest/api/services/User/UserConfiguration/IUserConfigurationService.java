package com.hosting.rest.api.services.User.UserConfiguration;

import com.hosting.rest.api.models.User.UserConfiguration.UserConfigurationModel;

public interface IUserConfigurationService {

	public UserConfigurationModel addNewUserConfiguration(final UserConfigurationModel newUserConfigurationModel);

	public UserConfigurationModel updateUserConfiguration(final Integer userId,
			final UserConfigurationModel userConfigurationModelToUpdate);

	public UserConfigurationModel findById(final Integer userConfigurationId);

	public UserConfigurationModel findByUserId(final Integer userId);

	public void deleteUserConfigurationByUserId(final Integer userId);
	
	public void deleteUserConfiguration(final Integer userConfigurationId);
}
