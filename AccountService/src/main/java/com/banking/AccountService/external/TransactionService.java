package com.banking.AccountService.external;

import com.banking.AccountService.model.dto.external.TransactionResponse;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name="transaction-service")
@LoadBalancerClient(name="transaction-service")
public interface TransactionService {

    @GetMapping("/api/transactions")
    List<TransactionResponse> getTransactionByAccountId(@RequestParam String accountId);
}
