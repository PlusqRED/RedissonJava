package com.redisson.controller;

import com.redisson.model.Movie;
import com.redisson.service.PrinterReactive;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RedissonClient;
import org.redisson.api.RedissonReactiveClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/movies")
public class HomeController {
    private final RedissonClient redissonClient;
    private final RedissonReactiveClient redissonReactiveClient;

    @GetMapping
    public ResponseEntity<Mono<String>> findAll() {
        return ResponseEntity.ok(redissonReactiveClient.getRemoteService().get(PrinterReactive.class).printSmth());
    }

    @PostMapping
    public ResponseEntity<String> insert(@RequestBody Movie movie) {
        return ResponseEntity.ok().build();
    }
}
