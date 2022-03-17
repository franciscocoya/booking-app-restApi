package com.hosting.rest.api.services.User;

import com.hosting.rest.api.models.User.UserModel;

import java.util.List;

/**
 * @author Francisco Coya · https://github.com/FranciscoCoya
 * @version v1.0.0
 * @description
 **/
public interface IUserService {

    public List<UserModel> getAllUsers();

}
