package com.ops.zuul.configuration;

import org.springframework.cloud.netflix.zuul.filters.SimpleRouteLocator;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;

import java.util.LinkedHashMap;

public class CustomZuulRouteLocator extends SimpleRouteLocator {

    public CustomZuulRouteLocator(String servletPath, ZuulProperties properties) {
        super(servletPath, properties);
    }

    @Override
    protected LinkedHashMap<String, ZuulProperties.ZuulRoute> locateRoutes() {
        // Define your routes programmatically
        LinkedHashMap<String, ZuulProperties.ZuulRoute> routes = new LinkedHashMap<>();

        // Example route: Map requests with path "/custom-path/**" to the service with ID "custom-service"
        ZuulProperties.ZuulRoute customRoute = new ZuulProperties.ZuulRoute();
        customRoute.setPath("/hello/**");
        customRoute.setServiceId("merchant-service");
        routes.put("/custom-path/**", customRoute);

        // Add more routes as needed

        return routes;
    }
}
