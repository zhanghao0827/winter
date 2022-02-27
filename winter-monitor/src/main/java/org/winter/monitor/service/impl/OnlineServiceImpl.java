package org.winter.monitor.service.impl;

import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.winter.common.constant.Constants;
import org.winter.common.util.RedisUtils;
import org.winter.monitor.service.OnlineService;
import org.winter.monitor.service.criteria.OnlineCriteria;
import org.winter.monitor.service.dto.Online;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class OnlineServiceImpl implements OnlineService {

    @Autowired
    private RedisUtils redisUtils;

    @Override
    public List<Online> getOnlineDtoList(OnlineCriteria online) {
        Set<String> keys = redisUtils.keys(Constants.ONLINE_ID_PREFIX + "*");
        List<Online> list = new ArrayList<>();
        for (String key : keys) {
            ObjectMapper objectMapper = new ObjectMapper();
            Online onlineDto = null;
            try {
                onlineDto = objectMapper.readValue(objectMapper.writeValueAsString(redisUtils.get(key)), Online.class);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            list.add(onlineDto);
        }
        if (StrUtil.isNotEmpty(online.getUsername())) {
            return list.stream().filter(monOnline -> monOnline.getUsername().equals(online.getUsername())).collect(Collectors.toList());
        }
        return list;
    }

    @Override
    public Long deleteBatch(List<String> ids) {
        List<String> list = new ArrayList<>();
        for (String id : ids) {
            list.add(Constants.ONLINE_ID_PREFIX + id);
        }
        return redisUtils.delete(list);
    }
}
