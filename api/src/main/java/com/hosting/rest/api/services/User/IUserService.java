package com.hosting.rest.api.services.User;

import java.util.List;

import com.hosting.rest.api.models.User.UserModel;

/**
 * @author Francisco Coya · https://github.com/FranciscoCoya
 * @version v1.0.0
 * @description
 **/
public interface IUserService {

	public UserModel updateUser(final Integer userId, final UserModel userToUpdate);

	public void deleteUserById(final Integer userId);

	public List<UserModel> findAllStartedUsers();

	public UserModel getUserById(final Integer userId);

	public List<UserModel> findAllUsers();
}
