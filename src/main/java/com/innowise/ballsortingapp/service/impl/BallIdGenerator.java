package com.innowise.ballsortingapp.service.impl;

import com.innowise.ballsortingapp.service.IdGenerator;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BallIdGenerator implements IdGenerator {

    private static IdGenerator instance;
    private static final Lock lock = new ReentrantLock();
    private static final AtomicBoolean created = new AtomicBoolean(false);

    private static final AtomicLong id = new AtomicLong(1);

    private BallIdGenerator() {

    }

    public static IdGenerator getInstance() {
        if (!created.get()) {
            try {
                lock.lock();
                if (instance == null) {
                    instance = new BallIdGenerator();
                    created.set(true);
                }
            } finally {
                lock.unlock();
            }
        }

        return instance;
    }

    @Override
    public long getId() {
        return id.getAndIncrement();
    }
}