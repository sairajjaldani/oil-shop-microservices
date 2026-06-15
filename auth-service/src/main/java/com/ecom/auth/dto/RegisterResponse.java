package com.ecom.auth.dto;

public record RegisterResponse(

        Long userId,
        String firstName,
        String email,
        String message

) {
}