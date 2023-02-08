package com.menstalk.userservice.user.service;

import com.menstalk.userservice.user.dto.UserResponse;
import com.menstalk.userservice.user.mapper.UserConvert;
import com.menstalk.userservice.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final UserConvert userConvert;

    @Override
    public UserResponse getByUserId(Long userId) {

        return userConvert.userConvertToUserResponse(userRepository.findById(userId).orElseThrow());
    }
}
