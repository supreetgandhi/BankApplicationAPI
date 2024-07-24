package com.banking.UserService.service.impl;

import com.banking.UserService.exception.ResourceNotFoundException;
import com.banking.UserService.external.AccountService;
import com.banking.UserService.model.Status;
import com.banking.UserService.model.dto.CreateUser;
import com.banking.UserService.model.dto.UserDto;
import com.banking.UserService.model.dto.UserUpdate;
import com.banking.UserService.model.dto.UserUpdateStatus;
import com.banking.UserService.model.dto.response.Response;
import com.banking.UserService.model.entity.User;
import com.banking.UserService.model.entity.UserProfile;
import com.banking.UserService.model.external.Account;
import com.banking.UserService.model.mapper.UserMapper;
import com.banking.UserService.repository.UserRepository;
import com.banking.UserService.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AccountService accountService;

    @Value("${spring.application.success}")
    private String responseCodeSuccess;

    @Value("${spring.application.failure}")
    private String responseCodeFailure;

    @Override
    public Response createUser(UserDto userDto) {

        UserProfile userProfile = UserProfile.builder()
                .firstName(userDto.getUserProfileDto().getFirstName())
                .lastName(userDto.getUserProfileDto().getLastName()).build();

        User user = User.builder()
                .emailId(userDto.getEmailId())
                .contactNo(userDto.getContactNo())
                .status(Status.PENDING)
                .userProfile(userProfile)
                .authId(userDto.getEmailId())
                .identificationNumber(UUID.randomUUID().toString())
                .build();

        // User user = UserMapper.mapToModel(userDto);
        User savedUser = userRepository.save(user);

        return Response.builder()
                .responseMessage("User created successfully")
                .responseCode(responseCodeSuccess)
                .userDto(UserMapper.mapToDto(savedUser))
                .build();
    }

    @Override
    public List<UserDto> readAllUsers() {
        List<User> users = userRepository.findAll();

        return users.stream().map(user -> {
            UserDto userDto = UserMapper.mapToDto(user);
                userDto.setUserId(user.getUserId());
                userDto.setEmailId(user.getEmailId());
                userDto.setIdentificationNumber(user.getIdentificationNumber());

            return userDto;
        }).collect(Collectors.toList());
    }

    @Override
    public UserDto readUser(String authId) {

        User user = userRepository.findUserByAuthId(authId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found on the server"));

        return UserMapper.mapToDto(user);
    }

    @Override
    public Response updateUserStatus(Long id, UserUpdateStatus userUpdateStatus) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found on the server"));

        user.setStatus(userUpdateStatus.getStatus());
        userRepository.save(user);

         return Response.builder()
                .responseMessage("User updated successfully")
                .responseCode(responseCodeSuccess)
                .userDto(UserMapper.mapToDto(user))
                .build();
    }

    @Override
    public Response updateUser(Long id, UserUpdate userUpdate) {

        User user= userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found on the server"));

        user.setContactNo(userUpdate.getContactNo());
        BeanUtils.copyProperties(userUpdate, user.getUserProfile());

        userRepository.save(user);

        return Response.builder()
                .responseCode(responseCodeSuccess)
                .responseMessage("User updated successfully")
                .userDto(UserMapper.mapToDto(user))
                .build();
    }

    @Override
    public UserDto readyUserById(Long userId) {
        return userRepository.findById(userId)
                .map(user -> UserMapper.mapToDto(user))
                .orElseThrow(()-> new ResourceNotFoundException("USer not found on the server"));
    }

    @Override
    public UserDto readUserByAccountNumber(String accountNumber) {

        ResponseEntity<Account> response = accountService.readByAccountNumber(accountNumber);
        if(Objects.isNull(response.getBody()))
        {
            throw new ResourceNotFoundException("Account Number not found on the server");
        }
        Long userId = response.getBody().getUserId();
        return userRepository.findById(userId)
                .map(user -> UserMapper.mapToDto(user))
                .orElseThrow(() -> new ResourceNotFoundException("User not found on the server"));
    }
}
