package org.winter.common.base;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Timestamp;

@Getter
@Setter
public class BaseDto implements Serializable {

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    @Excel(name = "创建时间", format = "yyyy-MM-dd HH:mm:ss", width = 25)
    private Timestamp createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    @Excel(name = "更新时间", format = "yyyy-MM-dd HH:mm:ss", width = 25)
    private Timestamp updateTime;

}
