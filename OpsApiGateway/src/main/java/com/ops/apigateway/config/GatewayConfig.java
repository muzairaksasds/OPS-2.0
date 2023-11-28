package com.ops.apigateway.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@Configuration
public class GatewayConfig {

    @Value("${jwt.secret}") // Load the secret key from application.properties or application.yml
    private String secretKey;

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("merchant_route", r -> r.path("/hello/**")
                        .uri("lb://merchant-service")) // Using Eureka service ID
                .route("auth_route", r -> r.path("/auth/**")
                        .uri("lb://auth-service")) // Using Eureka service ID

                .build();
    }

    @Bean
    public GatewayFilter jwtValidationFilter() {
        return new AbstractGatewayFilterFactory<Object>() {
            @Override
            public GatewayFilter apply(Object config) {
                return (exchange, chain) -> {
                    String token = exchange.getRequest().getHeaders().getFirst("Authorization");
                    if (token == null || !validateJwt(token)) {
                        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid JWT token");
                    }
                    return chain.filter(exchange);
                };
            }
        }.apply(null);
    }

    public boolean validateJwt(String token) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(secretKey.getBytes())
                    .build()
                    .parseClaimsJws(token.replace("Bearer ", ""))
                    .getBody();

            System.out.println("Decoded MSISDN: " + claims.get("msisdn"));
            System.out.println("Decoded AppID: " + claims.get("appid"));
            System.out.println("Decoded Secret Code: " + claims.get("secretCode"));

            return true;
        } catch (Exception e) {
            // Token validation failed
            e.printStackTrace(); // Log the exception or handle it as needed
            return false;
        }
    }

}
