package org.winter.monitor.service.criteria;

import lombok.Getter;
import lombok.Setter;
import org.winter.common.annotation.QueryCriteria;
import org.winter.common.enums.QueryType;

import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
public class LogCriteria {

    @QueryCriteria(type = QueryType.LIKE)
    private String content;

    @QueryCriteria(type = QueryType.LIKE)
    private String username;

    @QueryCriteria(type = QueryType.EQUALS)
    private String requestMethod;

    @QueryCriteria(type = QueryType.EQUALS)
    private String ip;

    @QueryCriteria(type = QueryType.EQUALS)
    private Boolean status;

    @QueryCriteria(type = QueryType.BETWEEN)
    private List<Timestamp> createTime;

}
