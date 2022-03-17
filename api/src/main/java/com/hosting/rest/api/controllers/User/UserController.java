package com.hosting.rest.api.controllers.User;

import com.hosting.rest.api.models.User.UserModel;
import com.hosting.rest.api.services.User.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
}
