package com.kafein.product.userservice.dto;

public record UserCreatePayloadDto(String name, String surname, String email, String password) {
}
