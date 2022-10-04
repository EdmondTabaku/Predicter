package com.example.predicter.exception.handler;

import com.example.predicter.exception.RequestTimeoutException;
import com.example.predicter.util.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RequestTimeoutExceptionHandler {

    @ExceptionHandler(value = {RequestTimeoutException.class})
    public ResponseEntity<?> handleRequestTimeoutException(RequestTimeoutException exception) {
        ErrorResponse errorResponse = new ErrorResponse(exception.getMessage(),
                HttpStatus.resolve(400));
        return new ResponseEntity<>(errorResponse, HttpStatus.resolve(400));
    }
}
