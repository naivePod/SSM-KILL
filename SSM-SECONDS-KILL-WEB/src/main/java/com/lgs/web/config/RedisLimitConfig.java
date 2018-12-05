package com.lgs.web.config;

import com.crossoverjie.distributed.constant.RedisToolsConstant;
import com.crossoverjie.distributed.limit.RedisLimit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

@Configuration
public class RedisLimitConfig {
    @Autowired
    JedisConnectionFactory jedisConnectionFactory;

    @Value("${redis.limit}")
    int limit;

    @Bean
    public RedisLimit build() {
        return new RedisLimit.Builder(jedisConnectionFactory, RedisToolsConstant.SINGLE).limit(limit).build();
    }
}
