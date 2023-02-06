package com.menstalk.userservice.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    @Column(name = "uname")
    private String name;
    private String username;
    private String email;
    @Column(name = "pwd")
    private String password;
    @Enumerated(EnumType.ORDINAL)
    private Role role;
    @Lob
    private byte[] profilePic;
    private LocalDateTime registerTime;
    private boolean emailVerified;

}
