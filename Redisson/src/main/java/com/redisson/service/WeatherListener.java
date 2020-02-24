package com.redisson.service;

import lombok.RequiredArgsConstructor;
import org.redisson.api.RedissonReactiveClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WeatherListener implements CommandLineRunner {
    private final RedissonReactiveClient redissonReactiveClient;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Listening...");
        redissonReactiveClient.getTopic("weather")
                .getMessages(Integer.class)
                .subscribe(System.out::println);
    }
}
