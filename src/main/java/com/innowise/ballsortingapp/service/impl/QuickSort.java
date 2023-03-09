package com.innowise.ballsortingapp.service.impl;

import com.innowise.ballsortingapp.entity.Ball;
import com.innowise.ballsortingapp.service.Sorter;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class QuickSort implements Sorter {

    private static Sorter instance;
    private static final Lock lock = new ReentrantLock();
    private static final AtomicBoolean created = new AtomicBoolean(false);

    private static final int WORKERS = 4;

    private QuickSort() {

    }

    public static Sorter getInstance() {
        if (!created.get()) {
            try {
                lock.lock();
                if (instance == null) {
                    instance = new QuickSort();
                    created.set(true);
                }
            } finally {
                lock.unlock();
            }
        }

        return instance;
    }

    private static class QuickSortAction<T> extends RecursiveAction {

        private final int threshold;
        private final int low;
        private final int high;
        private final List<T> elements;
        private final Comparator<T> comparator;

        public QuickSortAction(List<T> elements, Comparator<T> comparator, int low, int high, int threshold) {
            this.elements = elements;
            this.comparator = comparator;
            this.low = low;
            this.high = high;
            this.threshold = threshold;
        }

        @Override
        public void compute() {
            if (high - low >= threshold) {
                int pivot = partition(elements, low, high, comparator);
                invokeAll(new QuickSortAction<>(elements, comparator, low, pivot, threshold),
                        new QuickSortAction<>(elements, comparator, pivot + 1, high, threshold));
            } else {
                quickSort(elements, low, high, comparator);
            }
        }

        private void quickSort(List<T> elements, int low, int high, Comparator<T> comparator) {
            if (low < high) {
                int pivot = partition(elements, low, high, comparator);
                quickSort(elements, low, pivot, comparator);
                quickSort(elements, pivot + 1, high, comparator);
            }
        }

        private int partition(List<T> balls, int low, int high, Comparator<T> comparator) {
            T pivot = elements.get(low);
            int i = low;
            int j = high;

            while (true) {
                while (comparator.compare(balls.get(i), pivot) < 0) {
                    i++;
                }

                while (comparator.compare(balls.get(j), pivot) > 0) {
                    j--;
                }

                if (i >= j) {
                    return j;
                }

                Collections.swap(balls, i, j);
                i++;
                j--;
            }
        }
    }

    @Override
    public <T> void sort(List<T> elements, Comparator<T> comparator) {
        int threshold = elements.size() / WORKERS;
        ForkJoinPool.commonPool().invoke(new QuickSortAction<>(elements, comparator, 0, elements.size() - 1, threshold));
    }
}