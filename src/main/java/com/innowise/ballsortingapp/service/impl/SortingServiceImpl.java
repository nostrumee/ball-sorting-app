package com.innowise.ballsortingapp.service.impl;

import com.innowise.ballsortingapp.entity.Basket;
import com.innowise.ballsortingapp.service.BallComparator;
import com.innowise.ballsortingapp.service.SortingAlgorithm;
import com.innowise.ballsortingapp.service.SortingService;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SortingServiceImpl implements SortingService {

    private static SortingService instance;
    private static final Lock lock = new ReentrantLock();
    private static final AtomicBoolean created = new AtomicBoolean(false);

    private SortingServiceImpl() {

    }

    public static SortingService getInstance() {
        if (!created.get()) {
            try {
                lock.lock();
                if (instance == null) {
                    instance = new SortingServiceImpl();
                    created.set(true);
                }
            } finally {
                lock.unlock();
            }
        }

        return instance;
    }

    @Override
    public void sort(Basket basket, BallComparator ballComparator, SortingAlgorithm sortingAlgorithm) {
        
    }
}