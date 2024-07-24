package com.banking.TransactionService.model.response;

import com.banking.TransactionService.model.dto.TransactionDto;
import com.banking.TransactionService.model.entity.Transaction;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Response {
    private String responseCode;
    private String message;
    private Transaction transaction;
}
