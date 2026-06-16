package com.ecom.auth.service;

import com.ecom.auth.dto.LoginRequest;
import com.ecom.auth.dto.LoginResponse;
import com.ecom.auth.dto.RegisterRequest;
import com.ecom.auth.dto.RegisterResponse;
import com.ecom.auth.entity.Role;
import com.ecom.auth.entity.User;
import com.ecom.auth.exception.InvalidCredentialsException;
import com.ecom.auth.exception.UserAlreadyExistsException;
import com.ecom.auth.repository.UserRepository;
import com.ecom.auth.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{
    private final UserRepository userRepository;
    private  final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
    public RegisterResponse register(RegisterRequest request) {
        if(userRepository.existsByEmail(request.email())){
            throw new UserAlreadyExistsException("Email already registered");
        }
        if(userRepository.existsByMobileNumber(request.mobileNumber())){
            throw new UserAlreadyExistsException("Mobile Nmber is already registered");
        }
        User user=User.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .mobileNumber(request.mobileNumber())
                .password(passwordEncoder.encode(request.password()))
                .role(Role.CUSTOMER)
                .build();

        User savedUser=userRepository.save(user);
        return new RegisterResponse(
                savedUser.getId(),
                savedUser.getFirstName(),
                savedUser.getEmail(),
                "User registered successfully"
        );
    }

    @Override
    public LoginResponse login(LoginRequest request) {
        User user=userRepository.findByEmail(request.email()).orElseThrow(
                ()->new InvalidCredentialsException("Invalid email or password")
        );
        if (!passwordEncoder.matches(
                request.password(),
                user.getPassword())) {

            throw new InvalidCredentialsException(
                    "Invalid email or password"
            );
        }
        String token = jwtService.generateToken(user);

        return new LoginResponse(
                token,
                "Bearer",
                user.getId(),
                user.getEmail(),
                user.getRole().name()
        );
    }
}
