package com.hosting.rest.api.services.User;

import com.hosting.rest.api.models.User.UserModel;

import java.util.List;
import java.util.Optional;

/**
 * @author Francisco Coya · https://github.com/FranciscoCoya
 * @version v1.0.0
 * @description
 **/
public interface IUserService {

    public List<UserModel> getAllUsers();
    public Optional<UserModel> getUserById(Integer userId);
    
}
