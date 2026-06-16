package com.ecom.auth.service;

import com.ecom.auth.dto.LoginRequest;
import com.ecom.auth.dto.LoginResponse;
import com.ecom.auth.dto.RegisterRequest;
import com.ecom.auth.dto.RegisterResponse;

public interface AuthService {

    RegisterResponse register(RegisterRequest request);

    LoginResponse login(LoginRequest request);
}
