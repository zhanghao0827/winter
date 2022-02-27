package org.winter.common.annotation;


import org.winter.common.enums.QueryType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface QueryCriteria {

    //查询的类型
    QueryType type();

    //关键字匹配的值
    String value() default "";

}
