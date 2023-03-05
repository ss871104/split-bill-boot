package com.menstalk.userservice.authentication.service;

import com.menstalk.userservice.authentication.dto.LoginRequest;
import com.menstalk.userservice.authentication.dto.RegisterRequest;
import com.menstalk.userservice.authentication.dto.TokenResponse;
import com.menstalk.userservice.authentication.dto.UserAuthResponse;

public interface AuthenticationService {
    public TokenResponse register(RegisterRequest registerRequest);
    public TokenResponse login(LoginRequest loginRequest);
    public UserAuthResponse authentication(String username);
    public void logout(String token);
    public boolean checkBlackList(String token);
}
