package com.banking.TransactionService.model.mapper;

import com.banking.TransactionService.model.dto.TransactionDto;
import com.banking.TransactionService.model.entity.Transaction;
import org.springframework.beans.BeanUtils;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class TransactionMapper {

    public static Transaction mapToEntity(TransactionDto transactionDto){
        Transaction transaction = new Transaction();
        if(!Objects.isNull(transactionDto))
        {
            BeanUtils.copyProperties(transactionDto, transaction);
        }
        return transaction;
    }

    public static TransactionDto mapToDto(Transaction transaction)
    {
        TransactionDto transactionDto = new TransactionDto();
        if(!Objects.isNull(transaction))
        {
            BeanUtils.copyProperties(transaction, transactionDto);
        }
        return transactionDto;
    }
}
