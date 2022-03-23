package com.hosting.rest.api.controllers.User;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hosting.rest.api.models.User.UserModel;
import com.hosting.rest.api.services.User.UserServiceImpl;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    /**
     *
     * @return Listado de todos los usuarios de la aplicación.
     */
    @GetMapping("")
    public List<UserModel> getAllUsers(){
        return userService.getAllUsers();
    }
    
    /**
     * 
     * @param userId
     * 
     * @return Usuario con el id <code>userId</code>
     */
    @GetMapping("{userId}")
    public UserModel getUserById(@PathVariable(value="userId") Integer userId) {
    	return userService.getUserById(userId).get();
    }

    // TODO: Listado de usuarios verificados

    // TODO: Listado de usuarios que son hosts
    
}
