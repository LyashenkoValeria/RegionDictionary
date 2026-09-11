package org.lyashenko.regiondict.controller;

import org.lyashenko.regiondict.exception.RegionAlreadyExists;
import org.lyashenko.regiondict.exception.RegionNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RegionExceptionHandler {

    @ExceptionHandler(RegionNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String regionNotFoundExceptionHandler(RegionNotFoundException exception) {
        exception.printStackTrace();
        return exception.getMessage();
    }

    @ExceptionHandler(RegionAlreadyExists.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String regionAlreadyExistsExceptionHandler(RegionAlreadyExists exception) {
        exception.printStackTrace();
        return exception.getMessage();
    }

}
