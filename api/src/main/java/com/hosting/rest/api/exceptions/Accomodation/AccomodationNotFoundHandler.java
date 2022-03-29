package com.hosting.rest.api.exceptions.Accomodation;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Francisco Coya · https://github.com/FranciscoCoya
 * @version v1.0.0
 * @description
 **/
public class AccomodationNotFoundHandler {
    @ResponseBody
    @ExceptionHandler(AccomodationNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String accomodationNotFoundHandler(AccomodationNotFoundException exception){
        return exception.getMessage();
    }
}
