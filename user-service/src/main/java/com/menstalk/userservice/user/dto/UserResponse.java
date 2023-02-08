package com.menstalk.userservice.user.dto;

import com.menstalk.userservice.user.domain.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private Long userId;
    private String name;
    private String username;
    private String email;
    private String password;
    @Enumerated(EnumType.ORDINAL)
    private Role role;
    private byte[] profilePic;
    private LocalDateTime registerTime;
    private boolean emailVerified;
}
