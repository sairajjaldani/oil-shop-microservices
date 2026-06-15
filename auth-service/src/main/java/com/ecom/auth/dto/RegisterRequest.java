package com.ecom.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record RegisterRequest(

        @NotBlank
        String firstName,

        String lastName,

        @NotBlank
        @Email
        String email,

        @NotBlank
        String mobileNumber,

        @NotBlank
        String password

) {
}
