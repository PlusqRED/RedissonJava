package com.redisson.service;

import lombok.RequiredArgsConstructor;
import org.redisson.api.RTopicReactive;
import org.redisson.api.RedissonReactiveClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class WeatherPublisher implements CommandLineRunner {
    private final RedissonReactiveClient redissonReactiveClient;

    @Override
    public void run(String... args) throws Exception {
        RTopicReactive weather = redissonReactiveClient
                .getTopic("weather");
        Flux.generate(() -> 0, (integer, synchronousSink) -> {
            synchronousSink.next(integer + 1);
            return integer + 1;
        })
                .delayElements(Duration.ofSeconds(1L))
                .flatMap(o -> {
                    System.out.println(o);
                    return weather.publish(o);
                })
                .subscribe();
    }
}
