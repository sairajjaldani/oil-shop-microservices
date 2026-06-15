package com.ecom.auth.service;

import com.ecom.auth.dto.RegisterRequest;
import com.ecom.auth.dto.RegisterResponse;
import com.ecom.auth.entity.Role;
import com.ecom.auth.entity.User;
import com.ecom.auth.exception.UserAlreadyExistsException;
import com.ecom.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{
    private final UserRepository userRepository;
    private  final PasswordEncoder passwordEncoder;
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
}
