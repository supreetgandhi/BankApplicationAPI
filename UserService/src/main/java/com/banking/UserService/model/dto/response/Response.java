package com.banking.UserService.model.dto.response;

import com.banking.UserService.model.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Response {

    private String responseCode;
    private String responseMessage;
    private UserDto userDto;
}
