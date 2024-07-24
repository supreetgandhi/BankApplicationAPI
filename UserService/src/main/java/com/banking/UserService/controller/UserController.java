package com.banking.UserService.controller;

import com.banking.UserService.model.Status;
import com.banking.UserService.model.dto.CreateUser;
import com.banking.UserService.model.dto.UserDto;
import com.banking.UserService.model.dto.UserUpdate;
import com.banking.UserService.model.dto.UserUpdateStatus;
import com.banking.UserService.model.dto.response.Response;
import com.banking.UserService.service.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/createUser")
    public ResponseEntity<Response> createUser(@RequestBody UserDto userDto)
    {
       Response response = userService.createUser(userDto);
       return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> readAllUsers(){
        List<UserDto> userDto = userService.readAllUsers();
        return ResponseEntity.ok(userDto);
    }

    @GetMapping("/auth/{authId}")
    public ResponseEntity<UserDto> readUserByAuthId(@PathVariable String authId)
    {
        UserDto userDto = userService.readUser(authId);
        return ResponseEntity.ok(userDto);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Response> updateUserStatus(@PathVariable Long id, @RequestBody UserUpdateStatus userUpdateStatus)
    {
        Response response = userService.updateUserStatus(id, userUpdateStatus);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response> updateUser(@PathVariable Long id, @RequestBody UserUpdate userUpdate)
    {
        Response response = userService.updateUser(id, userUpdate);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> readByUserId(@PathVariable Long userId)
    {
        UserDto userDto = userService.readyUserById(userId);
        return ResponseEntity.ok(userDto);
    }

    @GetMapping("/accounts/{accountNumber}")
    // @CircuitBreaker(name = "accountBreaker", fallbackMethod = "accountFallback")
    // @Retry(name= "accountService", fallbackMethod = "accountFallback")
    @RateLimiter(name = "accountRateLimiter", fallbackMethod = "accountFallback")
    public ResponseEntity<UserDto> readUserByAccountNumber(@PathVariable String accountNumber)
    {
        UserDto userDto = userService.readUserByAccountNumber(accountNumber);
        return ResponseEntity.ok(userDto);
    }

    //private methods
    //creating fallback methods for circuitbreaker

    private ResponseEntity<UserDto> accountFallback(String accountNumber, Exception ex)
    {
        UserDto userDto = UserDto.builder()
                .userId(0L)
                .authId("1234")
                .status(Status.REJECTED)
                .emailId("demo@gmail.com")
                .build();
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }
}
