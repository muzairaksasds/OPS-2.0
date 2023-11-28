package com.ops.apigateway.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("merchant_route", r -> r.path("/hello/**")
                        .uri("lb://merchant-service")) // Using Eureka service ID
                .route("auth_route", r -> r.path("/auth/**")
                        .uri("lb://auth-service")) // Using Eureka service ID

                .build();
    }
}