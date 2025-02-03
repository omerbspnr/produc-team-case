package com.kafein.product.userservice.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UpdateUserRequest(

        @NotBlank(message = "Ad boş olamaz")
        String name,
        @NotBlank(message = "Soyad boş olamaz")
        String surname,
        @Email
        String email,
        @Size(min = 6, max = 18, message = "password en az 6 en fazla 16 karakter içerebilir.")
        String password) {
}
