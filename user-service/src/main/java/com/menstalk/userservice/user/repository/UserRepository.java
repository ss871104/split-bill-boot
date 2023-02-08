package com.menstalk.userservice.user.repository;

import com.menstalk.userservice.user.domain.User;
import com.menstalk.userservice.authentication.dto.UserAuthResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("select u from User u where u.username = :usernameOrEmail or u.email = :usernameOrEmail")
    Optional<UserAuthResponse> findByUsernameOrEmail(@Param("usernameOrEmail") String usernameOrEmail);
}
