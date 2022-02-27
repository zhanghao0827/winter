package org.winter.common.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Collection;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class RedisUtils {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 根据 key 获取过期时间 (秒)
     *
     * @param key 键 不能为null
     * @return 时间(秒) 返回0代表为永久有效
     */
    public Long getExpire(String key) {
        return redisTemplate.getExpire(key, TimeUnit.SECONDS);
    }

    /**
     * 指定缓存失效时间
     *
     * @param key      键
     * @param time     时间(秒)
     * @param timeUnit 单位
     */
    public boolean expire(String key, long time, TimeUnit timeUnit) {
        try {
            if (time > 0) {
                redisTemplate.expire(key, time, timeUnit);
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * 普通缓存放入并设置时间
     *
     * @param key      键
     * @param value    值
     * @param time     时间
     * @param timeUnit 类型
     */
    public void set(String key, Object value, long time, TimeUnit timeUnit) {
        if (time > 0) {
            redisTemplate.opsForValue().set(key, value, time, timeUnit);
        }
    }

    public Object get(String key) {
        return key == null ? null : redisTemplate.opsForValue().get(key);
    }

    /**
     * 查找匹配key
     *
     * @param pattern key
     * @return /
     */
    public Set<String> keys(String pattern) {
        return redisTemplate.keys(pattern);
    }

    /**
     * 删除缓存
     *
     * @param collection 可以传一个值 或多个
     */
    public Long delete(Collection<String> collection) {
        return redisTemplate.delete(collection);
    }

    /**
     * 删除缓存
     *
     * @param key 缓存key
     */
    public Boolean delete(String key) {
        return redisTemplate.delete(key);
    }
}
