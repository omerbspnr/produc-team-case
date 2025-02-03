package com.kafein.product.userservice.controller;

import com.kafein.product.userservice.dto.UpdateUserRequest;
import com.kafein.product.userservice.dto.UserDto;
import com.kafein.product.userservice.service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService)
    {
        this.userService = userService;
    }


    @GetMapping
    public ResponseEntity<List<UserDto>> findUsersByName(@RequestParam String name) {
        return ResponseEntity.ok(userService.findUsersByName(name));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> findUserById(@PathVariable String userId)
    {
        return ResponseEntity.ok(userService.findUserById(userId));
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(@PathVariable String userId, @Valid @RequestBody UpdateUserRequest updateUserRequest)
    {
         return ResponseEntity.ok(userService.updateUser(updateUserRequest, userId));

    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable String userId)
    {
        userService.deleteUser(userId);
        return ResponseEntity.ok().build();

    }

}
