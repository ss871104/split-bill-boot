package com.menstalk.apigateway.jwt;

import com.menstalk.apigateway.dto.UserAuthResponse;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SignatureException;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.function.Predicate;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter implements GlobalFilter , Ordered {
    private final JwtUtil jwtUtil;
    private final AuthorizationClient authorizationClient;
    private final JwtExceptionHandler jwtExceptionHandler;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = (ServerHttpRequest) exchange.getRequest();

        final List<String> apiEndpoints = List.of("/api/auth/register", "/api/auth/login", "/api/auth/authentication", "/api/auth/logout", "/api/auth/checkBlackList");

        Predicate<ServerHttpRequest> isApiSecured = r -> apiEndpoints.stream()
                .noneMatch(uri -> r.getURI().getPath().contains(uri));

        if (isApiSecured.test(request)) {
            if (!request.getHeaders().containsKey("Authorization")) {

                return jwtExceptionHandler.tokenException(exchange, HttpStatus.UNAUTHORIZED, "No token found");
            }

            final String token = request.getHeaders().getOrEmpty("Authorization").get(0);

            if (!token.startsWith("Bearer ")) {

                return jwtExceptionHandler.tokenException(exchange, HttpStatus.UNAUTHORIZED, "Invalid token");

            }

            final String jwt = token.substring(7);

            if (this.authorizationClient.ifTokenInBlackList(jwt, exchange)) {

                return jwtExceptionHandler.tokenException(exchange, HttpStatus.BAD_REQUEST, "Jwt Token blackList");
            }

            try {
                String username = this.jwtUtil.extractUsername(jwt);
                UserAuthResponse userDetails = this.authorizationClient.findByUsername(username, exchange).block();

                if (username != null && jwtUtil.isTokenValid(jwt, userDetails)) {
                    exchange.getRequest().mutate().header("id", userDetails.getUserId() + "").build();
                } else{

                    return jwtExceptionHandler.tokenException(exchange, HttpStatus.BAD_REQUEST, "Invalid JWT token");
                }
            } catch (SignatureException ex) {

                return jwtExceptionHandler.tokenException(exchange, HttpStatus.BAD_REQUEST, "Invalid JWT signature");

            } catch (MalformedJwtException ex) {

                return jwtExceptionHandler.tokenException(exchange, HttpStatus.BAD_REQUEST, "Invalid JWT token");
            } catch (ExpiredJwtException ex) {

                return jwtExceptionHandler.tokenException(exchange, HttpStatus.BAD_REQUEST, "Expired JWT token");
            } catch (UnsupportedJwtException ex) {

                return jwtExceptionHandler.tokenException(exchange, HttpStatus.BAD_REQUEST, "Unsupported JWT token");
            } catch (IllegalArgumentException ex) {

                return jwtExceptionHandler.tokenException(exchange, HttpStatus.BAD_REQUEST, "JWT claims string is empty");
            }
        }
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
