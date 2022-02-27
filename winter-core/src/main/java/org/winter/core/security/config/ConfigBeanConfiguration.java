package org.winter.core.security.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.winter.core.security.config.bean.JwtProperties;

@Configuration
public class ConfigBeanConfiguration {

    @Bean
    @ConfigurationProperties(prefix = "jwt")
    public JwtProperties jwtProperties() {
        return new JwtProperties();
    }

}
