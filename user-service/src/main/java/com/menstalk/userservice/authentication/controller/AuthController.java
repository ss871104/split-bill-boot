package com.menstalk.userservice.authentication.controller;

import com.menstalk.userservice.authentication.dto.LoginRequest;
import com.menstalk.userservice.authentication.dto.RegisterRequest;
import com.menstalk.userservice.authentication.dto.TokenResponse;
import com.menstalk.userservice.authentication.dto.UserAuthResponse;
import com.menstalk.userservice.authentication.handler.UsernameDuplicateException;
import com.menstalk.userservice.authentication.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationService authService;

    @PostMapping("/register")
    public ResponseEntity<TokenResponse> register(@RequestBody @Valid RegisterRequest registerRequest) {
        try {
            return ResponseEntity.ok(authService.register(registerRequest));
        } catch (UsernameDuplicateException e) {
            return new ResponseEntity<>(new TokenResponse("Username or Email duplicate", false), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@RequestBody LoginRequest loginRequest) {
        try {
            return ResponseEntity.ok(authService.login(loginRequest));
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(new TokenResponse("Wrong Username or Password", false), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/authentication")
    public ResponseEntity<UserAuthResponse> authentication(@RequestBody String username) {
        return ResponseEntity.ok(authService.authentication(username));
    }

    @GetMapping("/logout")
    public void logout(@RequestHeader(value = "Authorization") String token) {
        authService.logout(token.substring(7));
    }

    @PostMapping("/checkBlackList")
    public ResponseEntity<Boolean> checkBlackList(@RequestBody String token) {
        return ResponseEntity.ok(authService.checkBlackList(token));
    }
}
