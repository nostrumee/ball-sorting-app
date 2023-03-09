package com.innowise.ballsortingapp.service.impl;

import com.innowise.ballsortingapp.service.Sorter;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MergeSort implements Sorter {

    private static Sorter instance;
    private static final Lock lock = new ReentrantLock();
    private static final AtomicBoolean created = new AtomicBoolean(false);

    private static final int WORKERS = 4;

    private MergeSort() {

    }

    public static Sorter getInstance() {
        if (!created.get()) {
            try {
                lock.lock();
                if (instance == null) {
                    instance = new MergeSort();
                    created.set(true);
                }
            } finally {
                lock.unlock();
            }
        }

        return instance;
    }

    private static class MergeSortAction<T> extends RecursiveAction {

        private final int threshold;
        private final List<T> elements;
        private final Comparator<T> comparator;

        public MergeSortAction(List<T> elements, Comparator<T> comparator, int threshold) {
            this.elements = elements;
            this.comparator = comparator;
            this.threshold = threshold;
        }

        @Override
        public void compute() {
            if (elements.size() >= threshold && elements.size() > 1) {
                int mid = elements.size() / 2;
                List<T> left = new ArrayList<>(elements.subList(0, mid));
                List<T> right = new ArrayList<>(elements.subList(mid, elements.size()));
                invokeAll(new MergeSortAction<>(left, comparator, threshold),
                        new MergeSortAction<>(right, comparator, threshold));
                merge(left, right);
            } else {
                mergeSort(elements);
            }
        }

        private void mergeSort(List<T> elements) {
            if (elements.size() < 2) {
                return;
            }

            int mid = elements.size() / 2;
            List<T> left = new ArrayList<>(elements.subList(0, mid));
            List<T> right = new ArrayList<>(elements.subList(mid, elements.size()));
            mergeSort(left);
            mergeSort(right);
            merge(left, right);
        }

        private void merge(List<T> left, List<T> right) {
            int i = 0;
            int j = 0;
            int k = 0;

            while (i < left.size() && j < right.size()) {
                if (comparator.compare(left.get(i), right.get(j)) < 0) {
                    elements.set(k++, left.get(i++));
                } else {
                    elements.set(k++, right.get(j++));
                }
            }

            while (i < left.size()) {
                elements.set(k++, left.get(i++));
            }

            while (j < right.size()) {
                elements.set(k++, right.get(j++));
            }
        }
    }

    @Override
    public <T> void sort(List<T> elements, Comparator<T> comparator) {
        int threshold = elements.size() / WORKERS;
        ForkJoinPool.commonPool().invoke(new MergeSortAction<>(elements, comparator, threshold));
    }
}