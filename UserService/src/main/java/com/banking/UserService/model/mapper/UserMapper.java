package com.banking.UserService.model.mapper;

import com.banking.UserService.model.dto.UserDto;
import com.banking.UserService.model.dto.UserProfileDto;
import com.banking.UserService.model.entity.User;
import com.banking.UserService.model.entity.UserProfile;
import org.springframework.beans.BeanUtils;

import java.util.Objects;

public class UserMapper {

    public static UserDto mapToDto(User user)
    {
        UserDto userDto = new UserDto();
        UserProfileDto userProfileDto = new UserProfileDto();

        if(!Objects.isNull(user)) {
            BeanUtils.copyProperties(user, userDto);
            if (!Objects.isNull(user.getUserProfile())) {
                BeanUtils.copyProperties(user.getUserProfile(), userProfileDto);
                userDto.setUserProfileDto(userProfileDto);
            }
        }
        return userDto;
    }

    public static User mapToModel(UserDto userDto){
        User user = new User();
        UserProfile userProfile = new UserProfile();

        if(!Objects.isNull(userDto)) {
            BeanUtils.copyProperties(userDto, user);
            if (!Objects.isNull(userDto.getUserProfileDto())) {
                BeanUtils.copyProperties(userDto.getUserProfileDto(), userProfile);
                user.setUserProfile(userProfile);
            }
        }
        return user;
    }
}
