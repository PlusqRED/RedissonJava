package com.redisson.service.impl;


import com.redisson.service.Printer;
import lombok.SneakyThrows;

public class FirstPrinter implements Printer {
    @Override
    @SneakyThrows
    public String printSmth() {
        Thread.sleep(5000);
        return "FIRST";
    }
}
