package com.banking.AccountService.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class AccountStatusException extends RuntimeException{
        public AccountStatusException(String message)
        {
            super(message);
        }
}
