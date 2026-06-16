package com.ecom.auth.dto;

public record ProfileResponse(
        Long userId,
        String email,
        String role
) {
}