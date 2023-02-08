package com.menstalk.userservice.authentication.service;

import com.menstalk.userservice.authentication.dto.LoginRequest;
import com.menstalk.userservice.authentication.dto.RegisterRequest;
import com.menstalk.userservice.authentication.dto.TokenResponse;
import com.menstalk.userservice.authentication.jwt.JwtUtil;
import com.menstalk.userservice.user.domain.Role;
import com.menstalk.userservice.authentication.dto.UserAuthResponse;
import com.menstalk.userservice.user.domain.User;
import com.menstalk.userservice.user.mapper.UserConvert;
import com.menstalk.userservice.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserConvert userConvert;
    private final JwtUtil jwtUtil;
    @Override
    public TokenResponse register(RegisterRequest registerRequest) {
        UserAuthResponse userAuthResponse = UserAuthResponse.builder()
                .name(registerRequest.getName())
                .username(registerRequest.getUsername())
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .role(Role.USER)
                .build();

        try {
            userRepository.save(userConvert.authConvertToUser(userAuthResponse));
            String jwtToken = jwtUtil.generateToken(userAuthResponse);

            return TokenResponse.builder().successful(true).token(jwtToken).build();
        } catch (Exception e) {
            e.printStackTrace();
            return TokenResponse.builder().successful(false).build();
        }

    }

    @Override
    public TokenResponse login(LoginRequest loginRequest) {
        User user = userRepository.findByUsernameOrEmail(loginRequest.getUsername()).orElseThrow();

        if (passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            String jwtToken = jwtUtil.generateToken(userConvert.userConvertToAuth(user));

            return TokenResponse.builder()
                    .successful(true)
                    .token(jwtToken)
                    .build();
        }
        return TokenResponse.builder()
                .successful(false)
                .build();

    }

    @Override
    public UserAuthResponse authentication(String username) {
        return userConvert.userConvertToAuth(userRepository.findByUsernameOrEmail(username).orElseThrow());
    }

}
