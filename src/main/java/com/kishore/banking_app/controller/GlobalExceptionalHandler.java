package com.kishore.banking_app.controller;

import com.kishore.banking_app.entity.ErrorResponse;
import com.kishore.banking_app.exception.IdNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionalHandler {
    @ExceptionHandler(IdNotFound.class)
    public ResponseEntity<ErrorResponse> idNotFound(IdNotFound e){
        ErrorResponse errorResponse = new ErrorResponse(LocalDateTime.now(), e.getMessage(), "Make sure u entered correct Id");
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
}
