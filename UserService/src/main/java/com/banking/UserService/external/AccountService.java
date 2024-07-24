package com.banking.UserService.external;

import com.banking.UserService.model.external.Account;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name= "account-service")
@LoadBalancerClient(name="account-service")
public interface AccountService {

    @GetMapping("/api/account")
    ResponseEntity<Account> readByAccountNumber(@RequestParam String accountNumber);

}
