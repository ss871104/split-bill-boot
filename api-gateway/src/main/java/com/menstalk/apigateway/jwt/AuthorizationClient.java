package com.menstalk.apigateway.jwt;

import com.menstalk.apigateway.dto.UserAuthResponse;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class AuthorizationClient {

    public Mono<UserAuthResponse> findByUsername(String username, ServerWebExchange exchange) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request =
                new HttpEntity<String>(username, headers);
        String url = "http://" + exchange.getRequest().getURI().getHost() + "/user-service/api/auth/authentication";
        ResponseEntity<UserAuthResponse> responseEntity = new RestTemplate().postForEntity(url, request, UserAuthResponse.class);
        UserAuthResponse userDetails = responseEntity
                .getBody();

        return Mono.just(userDetails);
    }

    public boolean ifTokenInBlackList(String token, ServerWebExchange exchange) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request =
                new HttpEntity<String>(token, headers);
        String url = "http://" + exchange.getRequest().getURI().getHost() + "/user-service/api/auth/checkBlackList";
        ResponseEntity<Boolean> responseEntity = new RestTemplate().postForEntity(url, request, Boolean.class);
        Boolean ifTokenInBlackList = responseEntity
                .getBody();

        return ifTokenInBlackList;
    }

}
