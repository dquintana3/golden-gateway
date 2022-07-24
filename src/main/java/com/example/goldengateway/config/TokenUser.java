package com.example.goldengateway.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "user")
@Data
public class TokenUser {
    private String username;
    private String password;
    private String secretKey;
}
