package com.menstalk.apigateway.jwt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class JwtExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(JwtExceptionHandler.class);

    public Mono<Void> tokenException(ServerWebExchange exchange, HttpStatus httpStatus, String msg) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(httpStatus);
        String errorMessage = msg;
        logger.error(msg);
        DataBuffer buffer = response.bufferFactory().wrap(errorMessage.getBytes());
        return response.writeWith(Mono.just(buffer));
    }
}
