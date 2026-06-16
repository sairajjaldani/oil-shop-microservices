package com.ecom.auth.dto;

public record LoginResponse(
        String accessToken,
        String tokenType,
        Long userId,
        String email,
        String role
) {
}
