package com.innowise.ballsortingapp.util;

import com.innowise.ballsortingapp.entity.Ball;

import java.util.Comparator;

public class BallComparator {

    private static final Comparator<Ball> BY_TYPE = Comparator.comparingInt(ball -> ball.getType().ordinal());
    private static final Comparator<Ball> BY_PRODUCER = Comparator.comparingInt(ball -> ball.getProducer().ordinal());
    private static final Comparator<Ball> BY_COLOR = Comparator.comparingInt(ball -> ball.getColor().ordinal());
    private static final Comparator<Ball> BY_SIZE = Comparator.comparingInt(Ball::getSize);
    private static final Comparator<Ball> BY_PRICE = Comparator.comparing(Ball::getPrice);

    private BallComparator() {

    }

    public static Comparator<Ball> byType() {
        return BY_TYPE;
    }

    public static Comparator<Ball> byProducer() {
        return BY_PRODUCER;
    }

    public static Comparator<Ball> byColor() {
        return BY_COLOR;
    }

    public static Comparator<Ball> bySize() {
        return BY_SIZE;
    }

    public static Comparator<Ball> byPrice() {
        return BY_PRICE;
    }

}