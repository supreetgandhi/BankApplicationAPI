package com.banking.TransactionService.exception;

import com.banking.TransactionService.model.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException ex){

        String message = ex.getMessage();
        ErrorResponse response = ErrorResponse.builder()
                .message(message)
                .status(HttpStatus.NOT_FOUND)
                .build();

        return new ResponseEntity<ErrorResponse>(response, HttpStatus.NOT_FOUND);
    }
}
