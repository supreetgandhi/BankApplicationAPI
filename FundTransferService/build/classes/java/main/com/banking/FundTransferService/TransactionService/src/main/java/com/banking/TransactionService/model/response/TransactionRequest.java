package com.banking.TransactionService.model.response;

import com.banking.TransactionService.model.TransactionStatus;
import com.banking.TransactionService.model.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionRequest {

    private String referenceId;
    private String accountId;
    private TransactionType transactionType;
    private BigDecimal amount;
    private LocalDateTime localDateTime;
    private TransactionStatus TransactionStatus;
    private String comments;
}
