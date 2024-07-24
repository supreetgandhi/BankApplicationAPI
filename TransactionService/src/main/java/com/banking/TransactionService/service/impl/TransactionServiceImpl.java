package com.banking.TransactionService.service.impl;

import com.banking.TransactionService.model.TransactionStatus;
import com.banking.TransactionService.model.TransactionType;
import com.banking.TransactionService.model.dto.TransactionDto;
import com.banking.TransactionService.model.entity.Transaction;
import com.banking.TransactionService.model.mapper.TransactionMapper;
import com.banking.TransactionService.model.response.Response;
import com.banking.TransactionService.model.response.ResponseMessage;
import com.banking.TransactionService.model.response.TransactionRequest;
import com.banking.TransactionService.repository.TransactionRepository;
import com.banking.TransactionService.service.TransactionService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Value("${spring.application.ok}")
    private String ok;

    @Override
    public Response addTransaction(TransactionDto transactionDto) {

        Transaction transaction = TransactionMapper.mapToEntity(transactionDto);
        transaction.setTransactionType(transactionDto.getTransactionType());
        transaction.setComments(transactionDto.getDescription());
        transaction.setStatus(TransactionStatus.COMPLETED);
        transaction.setReferenceId(UUID.randomUUID().toString());

        transactionRepository.save(transaction);
        return Response.builder()
                .message("Transaction completed successfully")
                .responseCode(ok)
                .transaction(transaction)
                .build();
    }

    @Override
    public ResponseMessage internalTransaction(List<TransactionDto> transactionsDto, String transactionReference) {
        List<Transaction> transactions = transactionsDto.stream().map(dto -> TransactionMapper.mapToEntity(dto)).collect(Collectors.toList());

        transactions.forEach(transaction -> {
            transaction.setTransactionType(TransactionType.INTERNAL_TRANSFER);
            transaction.setStatus(TransactionStatus.COMPLETED);
            transaction.setReferenceId(transactionReference);
        });

        transactionRepository.saveAll(transactions);

        return ResponseMessage.builder()
                .responseCode(ok)
                .message("Transaction completed successfully")
                .build();
    }

    @Override
    public List<TransactionRequest> getTransactionByAccountId(String accountId) {
        List<Transaction> transactions = transactionRepository.findTransactionByAccountId(accountId);

        return transactions.stream().map(transaction -> {
            TransactionRequest transactionRequest = new TransactionRequest();
            BeanUtils.copyProperties(transaction, transactionRequest);
            transactionRequest.setTransactionStatus(transaction.getStatus());
            transactionRequest.setLocalDateTime(transaction.getTransactionDate());
            transactionRequest.setTransactionType(transaction.getTransactionType());

            return transactionRequest;
        }).collect(Collectors.toList());
    }

    @Override
    public List<TransactionRequest> getTransactionByTransactionReference(String transactionReference) {
        List<Transaction> transactionList = transactionRepository.findTransactionByReferenceId(transactionReference);

        return transactionList.stream().map(transaction -> {
            TransactionRequest transactionRequest = new TransactionRequest();
            BeanUtils.copyProperties(transaction, transactionRequest);

            transactionRequest.setTransactionStatus(transaction.getStatus());
            transactionRequest.setLocalDateTime(transaction.getTransactionDate());
            transactionRequest.setTransactionType(transaction.getTransactionType());

            return transactionRequest;
        }).collect(Collectors.toList());
    }
}
