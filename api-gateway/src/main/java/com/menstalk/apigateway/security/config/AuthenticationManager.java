package com.menstalk.apigateway.security.config;

import com.menstalk.apigateway.security.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class AuthenticationManager implements ReactiveAuthenticationManager {
    private JwtUtil jwtUtil;
    private UserDetailsService userDetailsService;

    @Override
    @SuppressWarnings("unchecked")
    public Mono<Authentication> authenticate(Authentication authentication) {
        String jwt = authentication.getCredentials().toString();
        String username = jwtUtil.extractUsername(jwt);
        UserDetails userDetails = this.userDetailsService.findByUsername(username).block();
        if (username != null && jwtUtil.isTokenValid(jwt, userDetails)) {
            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                    userDetails,
                    null,
                    userDetails.getAuthorities()
            );
            return Mono.just(auth);
        } else{
            return Mono.empty();
        }
    }
}

