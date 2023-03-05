package com.menstalk.apigateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
public class WebSecurityConfiguration {
    @Bean
    SecurityWebFilterChain SecurityWebFilterChain(ServerHttpSecurity http) {
            return http
                    .httpBasic().disable()
                    .formLogin().disable()
                    .csrf().disable()
                    .build();
    }

}
