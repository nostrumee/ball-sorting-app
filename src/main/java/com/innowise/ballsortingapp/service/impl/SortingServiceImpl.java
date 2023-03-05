package com.innowise.ballsortingapp.service.impl;

import com.innowise.ballsortingapp.entity.Ball;
import com.innowise.ballsortingapp.exception.SortingParametersNotSpecifiedException;
import com.innowise.ballsortingapp.service.SortingAlgorithm;
import com.innowise.ballsortingapp.service.SortingService;

import java.util.*;

public class SortingServiceImpl implements SortingService {
    private static final Comparator<Ball> BY_TYPE = Comparator.comparingInt(ball -> ball.getType().ordinal());
    private static final Comparator<Ball> BY_PRODUCER = Comparator.comparingInt(ball -> ball.getProducer().ordinal());
    private static final Comparator<Ball> BY_COLOR = Comparator.comparingInt(ball -> ball.getColor().ordinal());
    private static final Comparator<Ball> BY_SIZE = Comparator.comparingInt(Ball::getSize);
    private static final Comparator<Ball> BY_PRICE = Comparator.comparing(Ball::getPrice);

    private final Set<Comparator<Ball>> comparatorSet;

    private SortingServiceImpl(Set<Comparator<Ball>> comparatorSet) {
        this.comparatorSet = comparatorSet;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private final Set<Comparator<Ball>> comparatorSet = new LinkedHashSet<>();

        public Builder byType() {
            comparatorSet.add(BY_TYPE);
            return this;
        }

        public Builder byProducer() {
            comparatorSet.add(BY_PRODUCER);
            return this;
        }

        public Builder byColor() {
            comparatorSet.add(BY_COLOR);
            return this;
        }

        public Builder bySize() {
            comparatorSet.add(BY_SIZE);
            return this;
        }

        public Builder byPrice() {
            comparatorSet.add(BY_PRICE);
            return this;
        }

        public SortingService build() throws SortingParametersNotSpecifiedException {
            if (comparatorSet.isEmpty()) {
                //log.error()
                throw new SortingParametersNotSpecifiedException();
            }
            return new SortingServiceImpl(comparatorSet);
        }

    }

    @Override
    public void sort(List<Ball> listOfBalls, SortingAlgorithm sortingAlgorithm) {

    }
}
