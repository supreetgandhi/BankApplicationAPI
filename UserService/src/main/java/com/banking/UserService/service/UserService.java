package com.banking.UserService.service;

import com.banking.UserService.model.dto.CreateUser;
import com.banking.UserService.model.dto.UserDto;
import com.banking.UserService.model.dto.UserUpdate;
import com.banking.UserService.model.dto.UserUpdateStatus;
import com.banking.UserService.model.dto.response.Response;

import java.util.List;

public interface UserService {

    Response createUser(UserDto userDto);
    List<UserDto> readAllUsers();
    UserDto readUser(String authId);
    Response updateUserStatus(Long id, UserUpdateStatus userUpdateStatus);
    Response updateUser(Long id, UserUpdate userUpdate);
    UserDto readyUserById(Long userId);
    UserDto readUserByAccountNumber(String accountNumber);

}
