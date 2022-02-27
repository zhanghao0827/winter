package org.winter.monitor.service;

import org.springframework.data.domain.Page;
import org.winter.monitor.domain.Log;
import org.winter.monitor.service.criteria.LogCriteria;

import java.util.List;

public interface LogService {

    void save(Log log);

    Page<Log> findList(LogCriteria log);

    int deleteBatch(List<Long> ids);
}
