package com.kafein.product.userservice.repository;

import com.kafein.product.userservice.dto.UserDto;
import com.kafein.product.userservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    List<User> findUsersByName(String name);

    Optional<User> findUserByEmail(String email);
}
