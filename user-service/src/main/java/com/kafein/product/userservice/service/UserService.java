package com.kafein.product.userservice.service;

import com.kafein.product.userservice.converter.UserConverter;
import com.kafein.product.userservice.dto.UpdateUserRequest;
import com.kafein.product.userservice.dto.UserCreatePayloadDto;
import com.kafein.product.userservice.dto.UserDto;
import com.kafein.product.userservice.entity.User;
import com.kafein.product.userservice.exception.UserNotFoundException;
import com.kafein.product.userservice.repository.UserRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder)
    {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void createUser(UserCreatePayloadDto userCreatePayloadDto)
    {

        User user = UserConverter.userDtoToUser(userCreatePayloadDto);
        user.setPassword(passwordEncoder.encode(userCreatePayloadDto.password()));
        userRepository.save(user);
    }




    @Cacheable(value = "user", key = "#id")
    public UserDto findUserById(String id)
    {
        return userRepository.findById(id).map(UserConverter::userToUserDto).orElseThrow(() -> new UserNotFoundException("user not found with given id"));
    }

    @Cacheable(value = "users", key = "#name")
    public List<UserDto> findUsersByName(String name) {
        return userRepository.findUsersByName(name).stream().map(UserConverter::userToUserDto).collect(Collectors.toList());
    }



    public Optional<User> findByEmail(String email)
    {
        return userRepository.findUserByEmail(email);
    }

    @Caching(
    put = {@CachePut(value = "user", key = "#id")},
            evict = {@CacheEvict(value = "users", allEntries = true)}
    )
    public UserDto updateUser(UpdateUserRequest updateUserRequest, String id) {
        Optional<User> optionalUser = userRepository.findById(id);

        if(optionalUser.isEmpty()) {
            throw new UserNotFoundException("invalid user id : " + id);
        }

        User user = UserConverter.updateUserRequestToUser(updateUserRequest);

        user.setPassword(passwordEncoder.encode(updateUserRequest.password()));
        user.setId(id);
        user = userRepository.save(user);

        return UserConverter.userToUserDto(user);
    }



    @Caching(
            evict = {@CacheEvict(value = "users", allEntries = true),
                    @CacheEvict(value = "user", key = "#id")}
    )
    public void deleteUser(String id)
    {
        userRepository.deleteById(id);
    }



}
