package org.winter.core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.winter.common.util.RedisUtils;

@Configuration
public class RedisConfig {

    @Bean
    public RedisUtils redisUtils() {
        return new RedisUtils();
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        //关联
        template.setConnectionFactory(factory);
        //设置key的序列化器  StringRedisSerializer
        template.setKeySerializer(new StringRedisSerializer());
        //设置value的序列化器
        template.setValueSerializer(new Jackson2JsonRedisSerializer<>(Object.class));
        return template;
    }

}
