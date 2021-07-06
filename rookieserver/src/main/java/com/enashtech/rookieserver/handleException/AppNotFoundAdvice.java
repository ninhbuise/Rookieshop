package com.enashtech.rookieserver.handleException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class AppNotFoundAdvice {
    //User not found handler
    @ResponseBody
    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String UsernotFoundHandler(UserNotFoundException ex) {
        return ex.getMessage();
    } 

    //Customer not found handler
    @ResponseBody
    @ExceptionHandler(CustomerNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String CustomernotFoundHandler(CustomerNotFoundException ex) {
        return ex.getMessage();
    }
}
