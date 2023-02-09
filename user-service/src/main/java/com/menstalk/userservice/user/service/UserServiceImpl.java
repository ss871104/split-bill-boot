package com.menstalk.userservice.user.service;

import com.menstalk.userservice.user.dto.UserResponse;
import com.menstalk.userservice.user.mapper.UserConvert;
import com.menstalk.userservice.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
@CacheConfig(cacheNames = "userService")
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final UserConvert userConvert;

    @Override
    @Cacheable(value="user", key ="#userId")
    public UserResponse getByUserId(Long userId) {

        return userConvert.userConvertToUserResponse(userRepository.findById(userId).orElseThrow());
    }
}
