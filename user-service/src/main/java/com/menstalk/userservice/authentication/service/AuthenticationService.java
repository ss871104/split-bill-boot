package com.menstalk.userservice.authentication.service;

import com.menstalk.userservice.authentication.dto.LoginRequest;
import com.menstalk.userservice.authentication.dto.RegisterRequest;
import com.menstalk.userservice.authentication.dto.TokenResponse;
import com.menstalk.userservice.authentication.dto.UserAuthResponse;

import java.util.Optional;

public interface AuthenticationService {
    public TokenResponse register(RegisterRequest registerRequest);
    public TokenResponse login(LoginRequest loginRequest);

    public Optional<UserAuthResponse> authentication(String username);
}
