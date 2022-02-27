package org.winter.monitor.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.winter.common.util.PageUtils;
import org.winter.common.util.QueryUtils;
import org.winter.monitor.domain.Log;
import org.winter.monitor.repository.LogRepository;
import org.winter.monitor.service.LogService;
import org.winter.monitor.service.criteria.LogCriteria;

import java.util.List;

@Service
public class LogServiceImpl implements LogService {

    @Autowired
    private LogRepository logRepository;

    @Override
    public void save(Log log) {
        logRepository.save(log);
    }

    @Override
    public Page<Log> findList(LogCriteria log) {
        Sort sort = Sort.by(Sort.Direction.DESC, "createTime");
        Pageable pageable = PageUtils.startPage(sort);
        return logRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryUtils.getPredicate(root, log, criteriaBuilder), pageable);
    }

    @Override
    public int deleteBatch(List<Long> ids) {
        int i = 0;
        for (Long id : ids) {
            logRepository.deleteById(id);
            i++;
        }
        return i;
    }
}
