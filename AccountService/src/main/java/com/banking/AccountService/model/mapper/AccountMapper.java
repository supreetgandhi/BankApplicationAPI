package com.banking.AccountService.model.mapper;

import com.banking.AccountService.model.dto.AccountDto;
import com.banking.AccountService.model.entity.Account;

public class AccountMapper {

    public static Account mapToEntity(AccountDto accountDto){
        return new Account(
          accountDto.getAccountId(),
          accountDto.getAccountNumber(),
          accountDto.getAccountType(),
          accountDto.getAccountStatus(),
          accountDto.getOpeningDate(),
          accountDto.getAvailableBalance(),
          accountDto.getUserId()
        );
    }

    public static AccountDto mapToDto(Account account){
        return new AccountDto(
                account.getAccountId(),
                account.getAccountNumber(),
                account.getAccountType(),
                account.getAccountStatus(),
                account.getOpeningDate(),
                account.getAvailableBalance(),
                account.getUserId()
        );
    }
}
