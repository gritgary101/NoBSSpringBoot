package com.gritgary.demo.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<SimpleResponse> handlerProductNotFoundException(CustomBaseException exception){
        return ResponseEntity.status(exception.getStatus()).body(exception.getSimpleResponse());
    }
}
