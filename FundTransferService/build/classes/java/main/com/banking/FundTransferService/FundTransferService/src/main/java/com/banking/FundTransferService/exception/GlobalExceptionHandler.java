package com.banking.FundTransferService.exception;

import com.banking.FundTransferService.model.dto.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Response> handleResourceNotFoundException(ResourceNotFoundException ex){

        String message = ex.getMessage();
        Response response = Response.builder()
                .message(message)
                .responseCode(HttpStatus.NOT_FOUND.toString())
                .build();

        return new ResponseEntity<Response>(response, HttpStatus.NOT_FOUND);
    }
}
