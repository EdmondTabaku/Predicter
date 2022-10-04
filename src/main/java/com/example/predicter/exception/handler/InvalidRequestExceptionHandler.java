package com.example.predicter.exception.handler;

import com.example.predicter.exception.InvalidRequestException;
import com.example.predicter.util.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class InvalidRequestExceptionHandler {

    @ExceptionHandler(value = {InvalidRequestException.class})
    public ResponseEntity<?> handleInvalidException(InvalidRequestException exception) {
        ErrorResponse errorResponse = new ErrorResponse(exception.getMessage(),
                HttpStatus.resolve(400));
        return new ResponseEntity<>(errorResponse, HttpStatus.resolve(400));
    }
}
