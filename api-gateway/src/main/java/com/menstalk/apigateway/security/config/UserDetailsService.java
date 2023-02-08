package com.menstalk.apigateway.security.config;

import com.menstalk.apigateway.security.dto.UserAuthResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserDetailsService implements ReactiveUserDetailsService {

    @Override
    public Mono<UserDetails> findByUsername(String username) {
        ResponseEntity<UserAuthResponse> responseEntity = new RestTemplate().getForEntity("http://localhost/user-service/api/auth/authentication", UserAuthResponse.class, username);

        UserAuthResponse userDetails = responseEntity
                .getBody();
//                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return Mono.just(userDetails);
    }

}
