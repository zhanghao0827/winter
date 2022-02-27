package org.winter.monitor.service.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Disk {

    private String name;

    private String type;

    private String dir;

    private String free;

    private String total;

    private String used;

    private Double usage;
}
