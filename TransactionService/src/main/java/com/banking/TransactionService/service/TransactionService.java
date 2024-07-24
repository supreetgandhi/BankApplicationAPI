package com.banking.TransactionService.service;

import com.banking.TransactionService.model.dto.TransactionDto;
import com.banking.TransactionService.model.response.Response;
import com.banking.TransactionService.model.response.ResponseMessage;
import com.banking.TransactionService.model.response.TransactionRequest;
import org.springframework.stereotype.Service;

import java.util.List;

public interface TransactionService {

    Response addTransaction(TransactionDto transactionDto);

    ResponseMessage internalTransaction(List<TransactionDto> transactionDto, String transactionReference);

    List<TransactionRequest> getTransactionByAccountId(String accountId);

    List<TransactionRequest> getTransactionByTransactionReference(String transactionReference);
}
