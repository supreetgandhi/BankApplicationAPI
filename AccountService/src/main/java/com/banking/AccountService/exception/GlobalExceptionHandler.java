package com.banking.AccountService.exception;

import com.banking.AccountService.model.dto.response.ErrorResponse;
import com.banking.AccountService.model.dto.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler{

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handlerResourceNotFoundException(ResourceNotFoundException ex)
    {
        String message = ex.getMessage();
        ErrorResponse response = ErrorResponse.builder()
                .message(message)
                .status(HttpStatus.NOT_FOUND)
                .build();
        return new ResponseEntity<ErrorResponse>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AccountStatusException.class)
    public ResponseEntity<ErrorResponse> handlerAccountStatusException(AccountStatusException ex)
    {
        String message = ex.getMessage();
        ErrorResponse response = ErrorResponse.builder()
                .message(message)
                .status(HttpStatus.NOT_FOUND)
                .build();
        return new ResponseEntity<ErrorResponse>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AccountNotFoundException.class)
    public ResponseEntity<ErrorResponse> handlerAccountNotFoundException(AccountNotFoundException ex)
    {
        String message = ex.getMessage();
        ErrorResponse response = ErrorResponse.builder()
                .message(message)
                .status(HttpStatus.NOT_FOUND)
                .build();
        return new ResponseEntity<ErrorResponse>(response, HttpStatus.NOT_FOUND);
    }
}
