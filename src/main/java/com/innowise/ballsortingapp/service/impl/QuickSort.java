package com.innowise.ballsortingapp.service.impl;

import com.innowise.ballsortingapp.entity.Ball;
import com.innowise.ballsortingapp.service.SortingAlgorithm;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class QuickSort implements SortingAlgorithm {

    private static SortingAlgorithm instance;
    private static final Lock lock = new ReentrantLock();
    private static final AtomicBoolean created = new AtomicBoolean(false);

    private static final int WORKERS = 4;

    private QuickSort() {

    }

    public static SortingAlgorithm getInstance() {
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

    private static class QuickSortAction extends RecursiveAction {

        private final int threshold;
        private final int low;
        private final int high;
        private final List<Ball> balls;
        private final Comparator<Ball> ballComparator;

        public QuickSortAction(List<Ball> balls, Comparator<Ball> ballComparator, int low, int high, int threshold) {
            this.balls = balls;
            this.ballComparator = ballComparator;
            this.low = low;
            this.high = high;
            this.threshold = threshold;
        }

        @Override
        public void compute() {
            if (high - low >= threshold) {
                int pivot = partition(balls, low, high, ballComparator);
                invokeAll(new QuickSortAction(balls, ballComparator, low, pivot, threshold),
                        new QuickSortAction(balls, ballComparator, pivot + 1, high, threshold));
            } else {
                quickSort(balls, low, high, ballComparator);
            }
        }

        private void quickSort(List<Ball> balls, int low, int high, Comparator<Ball> ballComparator) {
            if (low < high) {
                int pivot = partition(balls, low, high, ballComparator);
                quickSort(balls, low, pivot, ballComparator);
                quickSort(balls, pivot + 1, high, ballComparator);
            }
        }

        private int partition(List<Ball> balls, int low, int high, Comparator<Ball> ballComparator) {
            Ball pivotBall = balls.get(low);
            int i = low;
            int j = high;

            while (true) {
                while (ballComparator.compare(balls.get(i), pivotBall) < 0) {
                    i++;
                }

                while (ballComparator.compare(balls.get(j), pivotBall) > 0) {
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
    public void sort(List<Ball> balls, Comparator<Ball> ballComparator) {
        int threshold = balls.size() / WORKERS;
        ForkJoinPool.commonPool().invoke(new QuickSortAction(balls, ballComparator, 0, balls.size() - 1, threshold));
    }
}
