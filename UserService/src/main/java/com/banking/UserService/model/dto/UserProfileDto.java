package com.banking.UserService.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserProfileDto {

    private Long userProfileId;

    private String firstName;

    private String lastName;

    private String gender;

    private String address;

    private String occupation;

    private String maritalStatus;

    private String nationality;

}
