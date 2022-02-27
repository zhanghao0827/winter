package org.winter.core.security.config.bean;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtProperties {

    private String header;

    private String prefix;

    private String secret;

    private Long expiration;
}
