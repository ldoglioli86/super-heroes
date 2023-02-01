package com.superheroes.redis;

import com.superheroes.model.SuperHero;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class RedisConfiguration {
    @Bean
    JedisConnectionFactory jedisConnectionFactory() {
        return new JedisConnectionFactory();
    }

    @Bean
    RedisTemplate<String, SuperHero> redisTemplate() {
        final RedisTemplate<String, SuperHero> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(jedisConnectionFactory());
        return redisTemplate;
    }
}
