package com.innowise.ballsortingapp.service.impl;

import com.innowise.ballsortingapp.entity.Ball;
import com.innowise.ballsortingapp.entity.Color;
import com.innowise.ballsortingapp.entity.Producer;
import com.innowise.ballsortingapp.entity.Type;
import com.innowise.ballsortingapp.service.Sorter;
import com.innowise.ballsortingapp.util.BallComparator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class QuickSortTest {

    private List<Ball> balls;
    private Sorter sorter;
    private List<Ball> expected;

    @BeforeEach
    void setUp() {
        sorter = QuickSort.getInstance();
        balls = Arrays.asList(
                new Ball(Type.BASEBALL, Producer.MIKASA, Color.GREEN, 1, new BigDecimal("1.36")),
                new Ball(Type.FOOTBALL, Producer.MOLTEN, Color.PURPLE, 4, new BigDecimal("10.54")),
                new Ball(Type.BASEBALL, Producer.SELECT, Color.GREEN, 2, new BigDecimal("5.56")),
                new Ball(Type.VOLLEYBALL, Producer.JOGEL, Color.ORANGE, 6, new BigDecimal("7.98")),
                new Ball(Type.HANDBALL, Producer.WILSON, Color.RED, 3, new BigDecimal("15.64")),
                new Ball(Type.BASKETBALL, Producer.MOLTEN, Color.PURPLE, 9, new BigDecimal("22.55"))
        );
        expected = new ArrayList<>(balls);
    }

    @Test
    void testSortByType() {
        sorter.sort(balls, BallComparator.byType());
        expected.sort(BallComparator.byType());
        assertEquals(expected.stream().map(Ball::getType).collect(Collectors.toList()),
                balls.stream().map(Ball::getType).collect(Collectors.toList()));
    }

    @Test
    void testSortByProducer() {
        sorter.sort(balls, BallComparator.byProducer());
        expected.sort(BallComparator.byProducer());
        assertEquals(expected.stream().map(Ball::getProducer).collect(Collectors.toList()),
                balls.stream().map(Ball::getProducer).collect(Collectors.toList()));
    }

    @Test
    void testSortByColor() {
        sorter.sort(balls, BallComparator.byColor());
        expected.sort(BallComparator.byColor());
        assertEquals(expected.stream().map(Ball::getColor).collect(Collectors.toList()),
                balls.stream().map(Ball::getColor).collect(Collectors.toList()));
    }

    @Test
    void testSortBySize() {
        sorter.sort(balls, BallComparator.bySize());
        expected.sort(BallComparator.bySize());
        assertEquals(expected.stream().map(Ball::getSize).collect(Collectors.toList()),
                balls.stream().map(Ball::getSize).collect(Collectors.toList()));
    }

    @Test
    void testSortByPrice() {
        sorter.sort(balls, BallComparator.byPrice());
        expected.sort(BallComparator.byPrice());
        assertEquals(expected.stream().map(Ball::getPrice).collect(Collectors.toList()),
                balls.stream().map(Ball::getPrice).collect(Collectors.toList()));
    }
}