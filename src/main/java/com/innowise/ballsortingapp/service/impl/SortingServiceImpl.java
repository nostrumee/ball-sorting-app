package com.innowise.ballsortingapp.service.impl;

import com.innowise.ballsortingapp.exception.ServiceException;
import com.innowise.ballsortingapp.service.Sorter;
import com.innowise.ballsortingapp.service.SortingService;

import java.util.Comparator;
import java.util.List;
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
    public <T> void sort(List<T> elements, Comparator<T> comparator, Sorter sorter) throws ServiceException {
        if (elements != null && comparator != null && sorter != null) {
            sorter.sort(elements, comparator);
        } else if (elements == null) {
            throw new ServiceException("elements for sorting not specified");
        } else if (comparator == null) {
            throw new ServiceException("comparator not specified");
        } else {
            throw new ServiceException("sorter not specified");
        }
    }
}