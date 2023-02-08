package com.menstalk.userservice.user.controller;

import com.menstalk.userservice.user.dto.UserResponse;
import com.menstalk.userservice.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponse> getByUserId(@PathVariable Long userId) {
        return new ResponseEntity<>(userService.getByUserId(userId), HttpStatus.OK);
    }

}
