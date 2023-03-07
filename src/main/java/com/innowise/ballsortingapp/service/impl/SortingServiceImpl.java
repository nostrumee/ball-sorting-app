package com.innowise.ballsortingapp.service.impl;

import com.innowise.ballsortingapp.entity.Ball;
import com.innowise.ballsortingapp.entity.Basket;
import com.innowise.ballsortingapp.exception.ServiceException;
import com.innowise.ballsortingapp.service.SortingAlgorithm;
import com.innowise.ballsortingapp.service.SortingService;

import java.util.Comparator;
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
    public void sort(Basket basket, Comparator<Ball> ballComparator, SortingAlgorithm sortingAlgorithm) {
        try {
            if (basket != null && ballComparator != null && sortingAlgorithm != null) {
                sortingAlgorithm.sort(basket.getBalls(), ballComparator);
            } else if (basket == null) {
                throw new ServiceException("basket not specified");
            } else if (ballComparator == null) {
                throw new ServiceException("sorting parameter not specified");
            } else {
                throw new ServiceException("sorting algorithm not specified");
            }
        } catch (ServiceException e) {
            //log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}