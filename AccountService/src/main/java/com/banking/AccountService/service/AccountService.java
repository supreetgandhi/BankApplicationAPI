package com.banking.AccountService.service;

import com.banking.AccountService.model.AccountStatus;
import com.banking.AccountService.model.dto.AccountDto;
import com.banking.AccountService.model.dto.AccountStatusUpdate;
import com.banking.AccountService.model.dto.external.TransactionResponse;
import com.banking.AccountService.model.dto.response.Response;
import com.banking.AccountService.model.dto.response.ResponseMessage;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AccountService {

    Response createAccount(AccountDto accountDto);
    Response updateStatus(String accountNumber, AccountStatusUpdate accountUpdate);
    AccountDto readAccountByAccountNumber(String accountNumber);
    Response updateAccount(String accountNumber, AccountDto accountDto);
    String getBalance(String accountNumber);
    List<TransactionResponse> getTransactionsFromAccountId(String accountId);
    ResponseMessage closeAccount(String accountNumber);
    AccountDto readAccountByUserId(Long userId);
}
