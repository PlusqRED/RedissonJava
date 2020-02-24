package com.redisson.service.impl;

import com.redisson.service.PrinterReactive;
import reactor.core.publisher.Mono;

import java.time.Duration;

public class FirstPrinterReactive implements PrinterReactive {
    @Override
    public Mono<String> printSmth() {
        return Mono.just("FIRST").delayElement(Duration.ofSeconds(3L));
    }
}
