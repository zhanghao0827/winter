package org.winter.monitor.service.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JVM {

    private String name;

    private String version;

    private String home;

    private String total;

    private String max;

    private String free;

}
