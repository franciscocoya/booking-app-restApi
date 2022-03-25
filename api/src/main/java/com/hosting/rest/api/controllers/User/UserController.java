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

    // TODO: Crear un nuevo usuario

    // TODO: Crear un nuevo usuario host

    @GetMapping("")
    public List<UserModel> getAllUsers(){
        return userService.listAllUsers();
    }
    
    @GetMapping("{userId}")
    public UserModel getUserById(@PathVariable(value="userId") Integer userId) {
    	return userService.getUserById(userId);
    }

    // TODO: Listado de usuarios verificados

    // TODO: Listado de usuarios que son hosts

    
}
