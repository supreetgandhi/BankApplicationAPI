package com.banking.AccountService.model.dto.response;

import com.banking.AccountService.model.dto.AccountDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Response {

    private HttpStatus status;
    private String message;
    private AccountDto accountDto;
}
