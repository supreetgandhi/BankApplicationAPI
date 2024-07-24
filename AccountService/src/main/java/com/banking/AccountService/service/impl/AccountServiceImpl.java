package com.banking.AccountService.service.impl;

import com.banking.AccountService.exception.AccountNotFoundException;
import com.banking.AccountService.exception.AccountStatusException;
import com.banking.AccountService.external.TransactionService;
import com.banking.AccountService.model.AccountStatus;
import com.banking.AccountService.model.dto.AccountDto;
import com.banking.AccountService.model.dto.AccountStatusUpdate;
import com.banking.AccountService.model.dto.external.TransactionResponse;
import com.banking.AccountService.model.dto.response.Response;
import com.banking.AccountService.model.dto.response.ResponseMessage;
import com.banking.AccountService.model.entity.Account;
import com.banking.AccountService.model.mapper.AccountMapper;
import com.banking.AccountService.repository.AccountRepository;
import com.banking.AccountService.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    TransactionService transactionService;

    @Override
    public Response createAccount(AccountDto accountDto) {
        Account account = AccountMapper.mapToEntity(accountDto);
        Account savedAccount = accountRepository.save(account);

        AccountDto newAccount = AccountMapper.mapToDto(savedAccount);
        return null;
    }

    @Override
    public Response updateStatus(String accountNumber, AccountStatusUpdate accountUpdate) {

        Account account = accountRepository.findAccountByAccountNumber(accountNumber);

        if(!account.getAccountStatus().equals(AccountStatus.ACTIVE)){
            throw new AccountStatusException("Account is inactive/closed");
        }
        account.setAccountStatus(accountUpdate.getAccountStatus());
        accountRepository.save(account);

        return Response.builder()
                .message("Account updated successfully")
                .status(HttpStatus.ACCEPTED)
                .accountDto(AccountMapper.mapToDto(account))
                .build();
    }

    @Override
    public AccountDto readAccountByAccountNumber(String accountNumber) {
        Account account = accountRepository.findAccountByAccountNumber(accountNumber);
        return AccountMapper.mapToDto(account);
    }

    @Override
    public Response updateAccount(String accountNumber, AccountDto accountDto) {

        Account account = accountRepository.findAccountByAccountNumber(accountNumber);
        if(Objects.isNull(account)) {
            throw new AccountNotFoundException("Account not found on the server");
        }
        accountRepository.save(account);

        return Response.builder()
                .message("Account successfully updated")
                .status(HttpStatus.OK)
                .accountDto(AccountMapper.mapToDto(account))
                .build();
    }

    @Override
    public String getBalance(String accountNumber) {
        Account account = accountRepository.findAccountByAccountNumber(accountNumber);
        return account.getAvailableBalance().toString();
    }

    @Override
    public List<TransactionResponse> getTransactionsFromAccountId(String accountId) {
        return transactionService.getTransactionByAccountId(accountId);
    }

    @Override
    public ResponseMessage closeAccount(String accountNumber) {
        Account account = accountRepository.findAccountByAccountNumber(accountNumber);
        if(Objects.isNull(account))
        {
            throw new AccountNotFoundException("No account found with account number :"+ accountNumber);
        }
        account.setAccountStatus(AccountStatus.CLOSED);
        return ResponseMessage.builder()
                .status(AccountStatus.CLOSED)
                .message("Account closed successfully")
                .build();
    }

    @Override
    public AccountDto readAccountByUserId(Long userId) {

        Account account = accountRepository.findAccountByUserId(userId);
            if(!account.getAccountStatus().equals(AccountStatus.ACTIVE))
            {
                throw new AccountStatusException("Account is inactive/closed");
            }
       AccountDto accountDto = AccountMapper.mapToDto(account);
        accountDto.setAccountStatus(account.getAccountStatus());
        accountDto.setAccountType(account.getAccountType());
        return accountDto;
    }
}
