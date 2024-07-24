package com.banking.AccountService.model.dto;

import com.banking.AccountService.model.AccountStatus;
import com.banking.AccountService.model.AccountType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountDto {
    private Long accountId;
    private String accountNumber;
    private AccountType accountType;
    private AccountStatus accountStatus;
    private LocalDate openingDate;
    private BigDecimal availableBalance;
    private Long userId;
}
