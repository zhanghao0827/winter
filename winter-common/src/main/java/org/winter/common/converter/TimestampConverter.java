package org.winter.common.converter;

import cn.hutool.core.util.StrUtil;
import org.springframework.core.convert.converter.Converter;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimestampConverter implements Converter<String, Timestamp> {

    @Override
    public Timestamp convert(String pattern) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date;
        if (StrUtil.isNotEmpty(pattern)) {
            try {
                date = format.parse(pattern);
                return new Timestamp(date.getTime());
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

}
