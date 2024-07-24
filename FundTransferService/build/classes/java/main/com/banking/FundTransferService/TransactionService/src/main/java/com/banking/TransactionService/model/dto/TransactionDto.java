package com.banking.TransactionService.model.dto;

import com.banking.TransactionService.model.TransactionStatus;
import com.banking.TransactionService.model.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionDto {

    private String accountId;
    private TransactionType transactionType;
    private BigDecimal amount;
    private String description;
}
