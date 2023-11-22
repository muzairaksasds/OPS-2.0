
package com.ops.zuul.configuration;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.ops.zuul.configuration.CustomZuulRouteLocator;

@Configuration
public class ZuulConfig {


    @Value("${server.servlet.context-path:/}")
    private String servletPath;

    private static final Logger logger = LoggerFactory.getLogger(ZuulConfig.class);
    @Bean
    public ZuulProperties zuulProperties() {
        return new ZuulProperties();
    }

    @Bean
    public CustomZuulRouteLocator customRouteLocator(ZuulProperties zuulProperties) {
        // Create a custom ZuulRouteLocator with programmatically defined routes
        return new CustomZuulRouteLocator(servletPath, zuulProperties);
    }
    @Bean
    public Object logRoutes(ZuulProperties zuulProperties) {
        if (zuulProperties != null && zuulProperties.getRoutes() != null) {
            zuulProperties.getRoutes().values().forEach(route -> {
                logger.info("Zuul route - path: {}, serviceId: {}, url: {}", route.getPath(), route.getServiceId(), route.getUrl());
            });
        } else {
            logger.warn("Zuul properties are not available for logging.");
        }

        // This is just to indicate in the logs that the routes have been logged
        logger.info("Zuul route logging completed.");

        return new Object(); // Return any non-null value
    }
}
