package org.winter.common.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NetInfo {

    private String ip = "Unknown";

    private String pro = "Unknown";

    private String proCode;

    private String city;

    private String cityCode;

    private String region;

    private String regionCode;

    private String addr;

    private String regionNames;

    private String err;

}
