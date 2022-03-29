package com.hosting.rest.api.services.User;

import java.util.List;

import com.hosting.rest.api.models.User.UserModel;

/**
 * @author Francisco Coya · https://github.com/FranciscoCoya
 * @version v1.0.0
 * @description
 **/
public interface IUserService {

	UserModel addNewUser(UserModel userToAdd);

	UserModel updateUser(Integer userId, UserModel userToUpdate);

	void deleteUserById(Integer userId);

	List<UserModel> findAllStartedUsers();

	UserModel getUserById(Integer userId);

	List<UserModel> findAllUsers();
}
