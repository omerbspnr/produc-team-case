package com.kafein.product.userservice.dto;

import java.io.Serializable;

public record UserDto(String id, String name, String surname, String email) implements Serializable {
}
