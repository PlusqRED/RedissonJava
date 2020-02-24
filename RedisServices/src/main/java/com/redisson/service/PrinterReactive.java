package com.redisson.service;

import org.redisson.api.annotation.RRemoteReactive;
import reactor.core.publisher.Mono;

@RRemoteReactive(Printer.class)
public interface PrinterReactive {

    Mono<String> printSmth();
}
