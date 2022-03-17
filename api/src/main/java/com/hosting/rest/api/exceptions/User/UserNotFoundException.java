package com.hosting.rest.api.exceptions.User;

public class UserNotFoundException extends RuntimeException{
    
    public UserNotFoundException(Integer userId){
        super("No se encontró ningún usuario con el id " + userId);
    }
}
