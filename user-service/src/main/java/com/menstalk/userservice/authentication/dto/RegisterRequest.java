package com.menstalk.userservice.authentication.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    @NotBlank(message = "Name is required")
    private String name;
    @Size(min = 8,  max = 20, message = "Length of Username must be 8 ~ 20")
    private String username;
    @Size(min = 8,  max = 20, message = "Length of Password must be 8 ~ 20")
    private String password;
    @Size(max = 255, message = "Length of Email must not exceed 255")
    @Email(message = "Invalid Email")
    @NotBlank(message = "Email is required")
    private String email;
}
