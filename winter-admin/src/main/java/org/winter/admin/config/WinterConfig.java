package org.winter.admin.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan(basePackages = "org.winter")
@EnableJpaRepositories(
        basePackages = {
                "org.winter.system.repository",
                "org.winter.monitor.repository"
        })
@EntityScan(
        basePackages = {
                "org.winter.system.domain",
                "org.winter.monitor.domain"
        })
public class WinterConfig {

}
