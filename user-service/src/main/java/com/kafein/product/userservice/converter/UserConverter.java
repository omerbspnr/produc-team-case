package com.kafein.product.userservice.converter;

import com.kafein.product.userservice.dto.UpdateUserRequest;
import com.kafein.product.userservice.dto.UserCreatePayloadDto;
import com.kafein.product.userservice.dto.UserDto;
import com.kafein.product.userservice.entity.User;

public final class UserConverter {


    public static User userDtoToUser(UserCreatePayloadDto userCreatePayloadDto)
    {
        User user = new User();

        user.setEmail(userCreatePayloadDto.email());
        user.setName(userCreatePayloadDto.name());
        user.setSurname(userCreatePayloadDto.surname());

        return user;
    }

    public static UserDto userToUserDto(User user) {
        return new UserDto(user.getId(), user.getName(), user.getSurname(), user.getEmail());
    }

    public static User updateUserRequestToUser(UpdateUserRequest updateUserRequest) {
        User user = new User();
        user.setEmail(updateUserRequest.email());
        user.setName(updateUserRequest.name());
        user.setSurname(updateUserRequest.surname());

        return user;
    }

}
