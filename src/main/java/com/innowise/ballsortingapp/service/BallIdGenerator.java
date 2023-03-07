package com.innowise.ballsortingapp.service;

import java.util.concurrent.atomic.AtomicLong;

public class BallIdGenerator {

    private static final AtomicLong id = new AtomicLong(1);

    public static long getId() {
        return id.getAndIncrement();
    }
}