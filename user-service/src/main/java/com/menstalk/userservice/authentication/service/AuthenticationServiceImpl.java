package com.menstalk.userservice.authentication.service;

import com.menstalk.userservice.authentication.dto.LoginRequest;
import com.menstalk.userservice.authentication.dto.RegisterRequest;
import com.menstalk.userservice.authentication.dto.TokenResponse;
import com.menstalk.userservice.authentication.handler.UsernameDuplicateException;
import com.menstalk.userservice.authentication.jwt.JwtUtil;
import com.menstalk.userservice.authentication.dto.UserAuthResponse;
import com.menstalk.userservice.user.domain.User;
import com.menstalk.userservice.user.mapper.UserConvert;
import com.menstalk.userservice.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

@Service
@Transactional
@RequiredArgsConstructor
@CacheConfig(cacheNames = "authService")
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserConvert userConvert;
    private final JwtUtil jwtUtil;
    private final RedisTemplate<String, String> redisTemplate;
    @Override
    public TokenResponse register(RegisterRequest registerRequest) {
        User user = User.builder()
                .name(registerRequest.getName())
                .username(registerRequest.getUsername())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .registerTime(LocalDateTime.now())
                .build();

        try {
        	user = userRepository.save(user);
        	userRepository.flush();
            String jwtToken = jwtUtil.generateToken(userConvert.userConvertToAuthResponse(user));
            
            redisTemplate.opsForValue().set("jwt:" + registerRequest.getUsername(), jwtToken, 3, TimeUnit.HOURS);

            return TokenResponse.builder().successful(true).token(jwtToken).build();
        } catch (Exception e) {
            throw new UsernameDuplicateException("username duplicate");
        }

    }

    @Override
    public TokenResponse login(LoginRequest loginRequest) {
        User user = userRepository.findByUsername(loginRequest.getUsername()).orElseThrow();

        if (passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            String redisJwt = redisTemplate.opsForValue().get("jwt:" + loginRequest.getUsername());
            if (redisJwt != null) {
                redisTemplate.opsForValue().set("blackList:" + redisJwt, redisJwt, 3, TimeUnit.HOURS);
            }

            String jwtToken = jwtUtil.generateToken(userConvert.userConvertToAuthResponse(user));

            redisTemplate.opsForValue().set("jwt:" + loginRequest.getUsername(), jwtToken, 3, TimeUnit.HOURS);

            return TokenResponse.builder()
                    .successful(true)
                    .token(jwtToken)
                    .build();
        } else {
            throw new NoSuchElementException("login fail");
        }

    }

    @Override
    @Cacheable(value="auth", key ="#username")
    public UserAuthResponse authentication(String username) {
        return userConvert.userConvertToAuthResponse(userRepository.findByUsername(username).orElseThrow());
    }

    @Override
    public void logout(String token) {
        redisTemplate.opsForValue().set("blackList:" + token, token, 3, TimeUnit.HOURS);
    }

    @Override
    public boolean checkBlackList(String token) {
        if (redisTemplate.hasKey("blackList:" + token))
            return true;
        else
            return false;
    }

}
