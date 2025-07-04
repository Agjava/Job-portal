package com.jobportal.gateway.config;

import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableZuulProxy
public class GatewayConfig {
    // Custom route locator is not strictly needed with application.yml, but can be added for advanced routing
} 