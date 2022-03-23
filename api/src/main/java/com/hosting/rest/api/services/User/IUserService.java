package com.hosting.rest.api.services.User;

import com.hosting.rest.api.models.User.UserModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Francisco Coya · https://github.com/FranciscoCoya
 * @version v1.0.0
 * @description
 **/
public interface IUserService {
    List<UserModel> getAllUsers();
    Optional<UserModel> getUserById(Integer userId);
}
