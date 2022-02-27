package org.winter.monitor.service;

import org.winter.monitor.service.criteria.OnlineCriteria;
import org.winter.monitor.service.dto.Online;

import java.util.List;

public interface OnlineService {

    List<Online> getOnlineDtoList(OnlineCriteria online);

    Long deleteBatch(List<String> ids);

}
