package org.winter.system.domain.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MetaVo {

    /**
     * the name show in sidebar and breadcrumb (recommend set)
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String title;

    /**
     * the icon show in the sidebar
     */

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String icon;

}
