package com.ecom.auth.controller;

import com.ecom.auth.dto.*;
import com.ecom.auth.entity.User;
import com.ecom.auth.repository.UserRepository;
import com.ecom.auth.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    private final UserRepository userRepository;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public RegisterResponse register(
            @Valid @RequestBody RegisterRequest request) {

        return authService.register(request);
    }

    @PostMapping("/login")
    public LoginResponse login(
            @Valid @RequestBody LoginRequest request) {

        return authService.login(request);
    }

    @GetMapping("/profile")
    public ProfileResponse profile(
            Authentication authentication) {

        User user =
                userRepository.findByEmail(
                                authentication.getName())
                        .orElseThrow();

        return new ProfileResponse(
                user.getId(),
                user.getEmail(),
                user.getRole().name()
        );
    }

    @GetMapping("/admin/dashboard")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminDashboard() {
        return "Admin Dashboard";
    }
}