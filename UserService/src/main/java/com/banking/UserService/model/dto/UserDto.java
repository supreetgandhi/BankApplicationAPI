package com.banking.UserService.model.dto;

import com.banking.UserService.model.Status;
import com.banking.UserService.model.entity.UserProfile;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {

    private Long userId;
    private String emailId;
    private String password;
    private String contactNo;
    private String identificationNumber;
    private String authId;
    private Status status;
    private UserProfileDto userProfileDto;
}
