package org.winter.system.domain.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserTemplateVo {

    @Excel(name = "用户名称")
    private String username;

    @Excel(name = "用户昵称")
    private String nickname;

    @Excel(name = "用户性别")
    private String sex;

    @Excel(name = "手机号码", width = 20)
    private String phone;

    @Excel(name = "电子邮箱", width = 20)
    private String email;

    /**
     * replace值替换  导出为：{source1_target1, source2_target2, ...} 导入为：{target1_source1, target2_source2, ...}
     */
    @Excel(name = "账号状态", replace = {"正常_true", "禁用_false"})
    private Boolean enabled;

}
