package org.winter.monitor.domain;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Timestamp;

@Entity(name = "mon_log")
@Getter
@Setter
public class Log {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Excel(name = "操作内容")
    private String content;

    private String method;

    @Excel(name = "请求方式")
    private String requestMethod;

    @Excel(name = "请求地址", width = 15)
    private String url;

    @Excel(name = "操作用户")
    private String username;

    @Excel(name = "所属IP", width = 20)
    private String ip;

    @Excel(name = "操作地点", width = 20)
    private String address;

    @Excel(name = "操作状态", replace = {"成功_true", "失败_false"})
    private Boolean status;

    private String result;

    private String exception;

    @CreationTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    @Excel(name = "操作时间", format = "yyyy-MM-dd HH:mm:ss", width = 25)
    private Timestamp createTime;

}
