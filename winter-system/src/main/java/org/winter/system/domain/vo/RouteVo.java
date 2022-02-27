package org.winter.system.domain.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RouteVo {

    private String name;

    private String path;

    private String component;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String redirect;

    private MetaVo meta;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<RouteVo> children;

}
