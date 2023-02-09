package com.menstalk.userservice.user.controller;

import com.menstalk.userservice.user.dto.UserResponse;
import com.menstalk.userservice.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/info")
    public ResponseEntity<UserResponse> getByUserId(@RequestHeader(name = "id") String userId) {
        return new ResponseEntity<>(userService.getByUserId(Long.valueOf(userId)), HttpStatus.OK);
    }

}
