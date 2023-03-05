package com.menstalk.userservice.user.service;

import com.menstalk.userservice.user.dto.UserResponse;

import java.util.List;

public interface UserService {
    UserResponse getByUserId(Long userId);
    UserResponse getByUsername(String username);
    List<UserResponse> getAllUsers();
}
