package com.banking.AccountService.exception;

public class AccountNotFoundException extends RuntimeException{

    public AccountNotFoundException(String message)
    {
        super(message);
    }

}
