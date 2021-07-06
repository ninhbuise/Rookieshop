package com.enashtech.rookieserver.handleException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class AppExceptionHadleAdvice {
    @ResponseBody
    @ExceptionHandler(NotFoundExecptionHandle.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String NotFoundExecptionHandle(NotFoundExecptionHandle ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(RuntimeExceptionHandle.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    String RuntimeExceptionHandle(RuntimeExceptionHandle ex) {
        return ex.getMessage();
    } 
}
