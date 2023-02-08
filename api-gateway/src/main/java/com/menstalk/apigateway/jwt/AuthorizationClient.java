package com.menstalk.apigateway.jwt;

import com.menstalk.apigateway.dto.UserAuthResponse;
import org.springframework.http.*;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@Component
public class AuthorizationClient {

    public Mono<UserAuthResponse> findByUsername(String username, ServerWebExchange exchange) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request =
                new HttpEntity<String>(username, headers);
        String url = "http://" + exchange.getRequest().getURI().getHost() + "/api/auth/authentication";
        ResponseEntity<UserAuthResponse> responseEntity = new RestTemplate().postForEntity(url, request, UserAuthResponse.class);
        UserAuthResponse userDetails = responseEntity
                .getBody();

        return Mono.just(userDetails);
    }

}
