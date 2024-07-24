package com.banking.TransactionService.controller;

import com.banking.TransactionService.model.dto.TransactionDto;
import com.banking.TransactionService.model.response.Response;
import com.banking.TransactionService.model.response.ResponseMessage;
import com.banking.TransactionService.model.response.TransactionRequest;
import com.banking.TransactionService.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping
    public ResponseEntity<Response> addTransactions(@RequestBody TransactionDto transactionDto)
    {
        Response response = transactionService.addTransaction(transactionDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/internal")
    public ResponseEntity<ResponseMessage> makeInternalTransaction(@RequestBody List<TransactionDto> transactionDtoList, @RequestParam String transactionReference)
    {
        ResponseMessage response = transactionService.internalTransaction(transactionDtoList, transactionReference);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<TransactionRequest>> getTransactionByAccountId(@RequestParam String accountId){
        List<TransactionRequest> transactionRequest = transactionService.getTransactionByAccountId(accountId);
        return new ResponseEntity<>(transactionRequest, HttpStatus.OK);
    }

    @GetMapping("/{referenceId}")
    public ResponseEntity<List<TransactionRequest>> getTransactionByTransactionReference(@PathVariable String referenceId)
    {
        List<TransactionRequest> transactionRequests = transactionService.getTransactionByTransactionReference(referenceId);
        return new ResponseEntity<>(transactionRequests, HttpStatus.OK);
    }
}
