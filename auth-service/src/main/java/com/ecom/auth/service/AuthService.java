package com.ecom.auth.service;

import com.ecom.auth.dto.RegisterRequest;
import com.ecom.auth.dto.RegisterResponse;

public interface AuthService {

    RegisterResponse register(RegisterRequest request);
}
