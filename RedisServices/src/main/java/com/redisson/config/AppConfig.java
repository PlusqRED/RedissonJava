package com.redisson.config;

import com.redisson.service.Printer;
import com.redisson.service.PrinterReactive;
import com.redisson.service.impl.FirstPrinter;
import com.redisson.service.impl.FirstPrinterReactive;
import lombok.SneakyThrows;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.api.RedissonReactiveClient;
import org.redisson.config.Config;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

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

    @EventListener(ApplicationReadyEvent.class)
    public void initApp() {
        redissonClient().getRemoteService().register(Printer.class, new FirstPrinter(), 2);
        redissonClient().getRemoteService().register(PrinterReactive.class, new FirstPrinterReactive(), 2);
    }
}
