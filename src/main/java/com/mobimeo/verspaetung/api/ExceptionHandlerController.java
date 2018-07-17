package com.mobimeo.verspaetung.api;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(value = MethodArgumentTypeMismatchException.class)
    public ResponseEntity localTimeConversionException(Exception e) {
        return new ResponseEntity<>(ExceptionUtils.getRootCauseMessage(e), HttpStatus.BAD_REQUEST);
    }
}