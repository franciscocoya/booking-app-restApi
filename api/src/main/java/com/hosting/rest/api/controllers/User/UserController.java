package com.hosting.rest.api.controllers.User;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    @PostMapping(value = "/new")
    public UserModel addNewUser(@RequestBody UserModel userToCreate) {
    	return userService.addNewUser(userToCreate);
    }

    @DeleteMapping(value = "{userId}")
    public void deleteUserById(@PathVariable(value="userId") Integer userId) {
    	userService.deleteUserById(userId);
    }
    
    @PutMapping("{userId}")
    public UserModel udpateUser(@PathVariable(name = "userId") Integer userId, @Valid @RequestBody UserModel userModelToUpdate) {
    	return userService.updateUser(userId, userModelToUpdate);
    }

    @GetMapping("all/started")
    public List<UserModel> getAllStartedUsers(){
        return userService.findAllStartedUsers();
    }
    
    @GetMapping("/all")
    public List<UserModel> getAllUsers(){
        return userService.findAllUsers();
    }
    
    @GetMapping("{userId}")
    public UserModel getUserById(@PathVariable(value="userId") Integer userId) {
    	return userService.getUserById(userId);
    }

    // TODO: Listado de usuarios verificados

    // TODO: Listado de usuarios que son hosts

    
}
