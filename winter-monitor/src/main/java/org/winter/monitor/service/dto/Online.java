package org.winter.monitor.service.dto;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Online {

    private String id;

    @Excel(name = "用户名称")
    private String username;

    @Excel(name = "所属IP", width = 20)
    private String ip;

    @Excel(name = "登录地点", width = 20)
    private String address;

    @Excel(name = "浏览器")
    private String browser;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    @Excel(name = "登录时间", format = "yyyy-MM-dd HH:mm:ss", width = 25)
    private Date loginTime;
}
