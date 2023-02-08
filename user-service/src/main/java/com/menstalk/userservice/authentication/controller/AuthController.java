package com.menstalk.userservice.authentication.controller;

import com.menstalk.userservice.authentication.dto.LoginRequest;
import com.menstalk.userservice.authentication.dto.RegisterRequest;
import com.menstalk.userservice.authentication.dto.TokenResponse;
import com.menstalk.userservice.authentication.dto.UserAuthResponse;
import com.menstalk.userservice.authentication.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin
public class AuthController {
    private final AuthenticationService authService;

    @PostMapping("/register")
    public ResponseEntity<TokenResponse> register(@RequestBody RegisterRequest registerRequest) {
        return ResponseEntity.ok(authService.register(registerRequest));
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(authService.login(loginRequest));
    }

    @PostMapping("/authentication")
    public ResponseEntity<UserAuthResponse> authentication(@RequestBody String username) {
        return ResponseEntity.ok(authService.authentication(username).orElseThrow(() -> new UsernameNotFoundException("User not found")));
    }
}
