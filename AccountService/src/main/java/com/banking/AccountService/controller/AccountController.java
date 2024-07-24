package com.banking.AccountService.controller;

import com.banking.AccountService.model.dto.AccountDto;
import com.banking.AccountService.model.dto.AccountStatusUpdate;
import com.banking.AccountService.model.dto.external.TransactionResponse;
import com.banking.AccountService.model.dto.response.Response;
import com.banking.AccountService.model.dto.response.ResponseMessage;
import com.banking.AccountService.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping
    public ResponseEntity<Response> createAccount(@RequestBody AccountDto accountDto)
    {
        Response response = accountService.createAccount(accountDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PatchMapping
    public ResponseEntity<Response> updateAccountStatus(@RequestParam String accountNumber, @RequestBody AccountStatusUpdate accountStatusUpdate){
        Response response = accountService.updateStatus(accountNumber, accountStatusUpdate);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<AccountDto> readByAccountNumber(@RequestParam String accountNumber){
        AccountDto accountDto = accountService.readAccountByAccountNumber(accountNumber);
        return ResponseEntity.ok(accountDto);
    }

    @PutMapping
    public ResponseEntity<Response> updateAccount(@RequestParam String accountNumber, @RequestBody AccountDto accountDto){
        Response response = accountService.updateAccount(accountNumber, accountDto);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/balance")
    public ResponseEntity<String> accountBalance(@RequestParam String accountNumber)
    {
        String balance = accountService.getBalance(accountNumber);
        return ResponseEntity.ok(balance);
    }

    @GetMapping("/transactions/{accountNumber}")
    public ResponseEntity<List<TransactionResponse>> getTransactionsFromAccountNumber(@PathVariable String accountNumber){
        List<TransactionResponse> response = accountService.getTransactionsFromAccountId(accountNumber);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/closure")
    public ResponseEntity<ResponseMessage> closeAccount(@RequestParam String accountNumber)
    {
        ResponseMessage response = accountService.closeAccount(accountNumber);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<AccountDto> readAccountByUserId(@PathVariable Long userId)
    {
        AccountDto accountDto = accountService.readAccountByUserId(userId);
        return ResponseEntity.ok(accountDto);
    }
}
