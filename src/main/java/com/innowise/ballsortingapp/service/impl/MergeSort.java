package com.innowise.ballsortingapp.service.impl;

import com.innowise.ballsortingapp.entity.Ball;
import com.innowise.ballsortingapp.service.SortingAlgorithm;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class MergeSort implements SortingAlgorithm {

    private static final int WORKERS = 4;

    private static class MergeSortAction extends RecursiveAction {

        private final int threshold;
        private final List<Ball> balls;
        private final Comparator<Ball> ballComparator;

        public MergeSortAction(List<Ball> balls, Comparator<Ball> ballComparator, int threshold) {
            this.balls = balls;
            this.ballComparator = ballComparator;
            this.threshold = threshold;
        }

        @Override
        public void compute() {
            if (balls.size() > threshold) {
                int mid = balls.size() / 2;
                List<Ball> left = new ArrayList<>(balls.subList(0, mid));
                List<Ball> right = new ArrayList<>(balls.subList(mid, balls.size()));
                invokeAll(new MergeSortAction(left, ballComparator, threshold),
                        new MergeSortAction(right, ballComparator, threshold));
                merge(left, right);
            }

            mergeSort(balls);
        }

        private void mergeSort(List<Ball> balls) {
            if (balls.size() < 2) {
                return;
            }

            int mid = balls.size() / 2;
            List<Ball> left = new ArrayList<>(balls.subList(0, mid));
            List<Ball> right = new ArrayList<>(balls.subList(mid, balls.size()));
            mergeSort(left);
            mergeSort(right);
            merge(left, right);
        }

        private void merge(List<Ball> left, List<Ball> right) {
            int i = 0;
            int j = 0;
            int k = 0;

            while (i < left.size() && j < right.size()) {
                if (ballComparator.compare(left.get(i), right.get(j)) < 1) {
                    balls.set(k++, left.get(i++));
                } else {
                    balls.set(k++, right.get(j++));
                }
            }

            while (i < left.size()) {
                balls.set(k++, left.get(i++));
            }

            while (j < right.size()) {
                balls.set(k++, right.get(j++));
            }
        }

    }

    @Override
    public void sort(List<Ball> balls, Comparator<Ball> ballComparator) {
        int threshold = balls.size() / WORKERS;
        ForkJoinPool.commonPool().invoke(new MergeSortAction(balls, ballComparator, threshold));
    }
}
