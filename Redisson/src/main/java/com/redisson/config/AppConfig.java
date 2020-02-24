package com.redisson.config;

import lombok.SneakyThrows;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.api.RedissonReactiveClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @SneakyThrows
    @Bean
    public RedissonClient redissonClient() {
        Config config = Config.fromYAML(getClass().getResource("/singleNodeConfig.yaml"));
        return Redisson.create(config);
    }

    @Bean
    @SneakyThrows
    public RedissonReactiveClient redissonReactiveClient() {
        Config config = Config.fromYAML(getClass().getResource("/singleNodeConfig.yaml"));
        return Redisson.createReactive(config);
    }

}
