package com.hosting.rest.api.services.User.UserHost;

import java.util.List;

import com.hosting.rest.api.models.User.UserHostModel;

public interface IUserHostService {

	public UserHostModel upgradeUserToUserHost(final Integer userId, final String userHostDni, final String userHostDirection);

	public UserHostModel updateUserHostById(final Integer userId, final UserHostModel userHostToUpdate);

	public void downgradeUserHostToUser(final Integer userId);
	
	public List<UserHostModel> findAll();
}
