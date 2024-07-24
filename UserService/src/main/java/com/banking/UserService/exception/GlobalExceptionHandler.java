package com.banking.UserService.exception;

import com.banking.UserService.model.dto.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException ex)
    {
        String message = ex.getMessage();
        ErrorResponse response = ErrorResponse.builder()
                                .message(message)
                                .isSuccess(true)
                                .status(HttpStatus.NOT_FOUND)
                                .build();
        return new ResponseEntity<ErrorResponse>(response, HttpStatus.NOT_FOUND);
    }
}
