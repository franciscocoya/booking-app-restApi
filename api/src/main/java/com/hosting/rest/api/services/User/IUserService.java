package com.hosting.rest.api.services.User;

import java.util.List;

import com.hosting.rest.api.models.User.UserHostModel;
import com.hosting.rest.api.models.User.UserModel;

/**
 * @author Francisco Coya · https://github.com/FranciscoCoya
 * @version v1.0.0
 * @description
 **/
public interface IUserService {
	
    List<UserModel> listAllUsers();
    
    UserModel getUserById(Integer userId);
    
    List<UserHostModel> listHostUsers();
    
    // TODO: Comprobar que un usuario es un host.
    boolean isUserHostByUserId(Integer userId);
    
    
    
}
