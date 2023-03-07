package com.innowise.ballsortingapp.service.impl;

import com.innowise.ballsortingapp.entity.Ball;
import com.innowise.ballsortingapp.entity.Color;
import com.innowise.ballsortingapp.entity.Producer;
import com.innowise.ballsortingapp.entity.Type;
import com.innowise.ballsortingapp.service.SortingAlgorithm;
import com.innowise.ballsortingapp.util.BallComparator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MergeSortTest {

    private static List<Ball> balls;
    private static SortingAlgorithm sortingAlgorithm;

    @BeforeEach
    public void setUp() {
        balls = Arrays.asList(
                new Ball(Type.BASEBALL, Producer.MIKASA, Color.GREEN, 1, new BigDecimal("1.36")),
                new Ball(Type.FOOTBALL, Producer.MOLTEN, Color.PURPLE, 4, new BigDecimal("10.54")),
                new Ball(Type.BASEBALL, Producer.SELECT, Color.GREEN, 2, new BigDecimal("30.56")),
                new Ball(Type.VOLLEYBALL, Producer.JOGEL, Color.ORANGE, 6, new BigDecimal("7.98")),
                new Ball(Type.HANDBALL, Producer.WILSON, Color.RED, 3, new BigDecimal("15.64")),
                new Ball(Type.BASKETBALL, Producer.MOLTEN, Color.PURPLE, 9, new BigDecimal("22.55"))
        );
        sortingAlgorithm = MergeSort.getInstance();
    }

    @Test
    public void testSortByType() {
        sortingAlgorithm.sort(balls, BallComparator.byType());
        assertEquals(Type.FOOTBALL, balls.get(0).getType());
        assertEquals(Type.BASKETBALL, balls.get(1).getType());
        assertEquals(Type.VOLLEYBALL, balls.get(2).getType());
        assertEquals(Type.HANDBALL, balls.get(3).getType());
        assertEquals(Type.BASEBALL, balls.get(4).getType());
        assertEquals(Type.BASEBALL, balls.get(5).getType());
    }

    @Test
    public void testSortByProducer() {
        sortingAlgorithm.sort(balls, BallComparator.byProducer());
        assertEquals(Producer.MIKASA, balls.get(0).getProducer());
        assertEquals(Producer.MOLTEN, balls.get(1).getProducer());
        assertEquals(Producer.MOLTEN, balls.get(2).getProducer());
        assertEquals(Producer.SELECT, balls.get(3).getProducer());
        assertEquals(Producer.JOGEL, balls.get(4).getProducer());
        assertEquals(Producer.WILSON, balls.get(5).getProducer());
    }

    @Test
    public void testSortByColor() {
        sortingAlgorithm.sort(balls, BallComparator.byColor());
        assertEquals(Color.RED, balls.get(0).getColor());
        assertEquals(Color.ORANGE, balls.get(1).getColor());
        assertEquals(Color.GREEN, balls.get(2).getColor());
        assertEquals(Color.GREEN, balls.get(3).getColor());
        assertEquals(Color.PURPLE, balls.get(4).getColor());
        assertEquals(Color.PURPLE, balls.get(5).getColor());
    }

    @Test
    public void testSortBySize() {
        sortingAlgorithm.sort(balls, BallComparator.bySize());
        assertEquals(1, balls.get(0).getSize());
        assertEquals(2, balls.get(1).getSize());
        assertEquals(3, balls.get(2).getSize());
        assertEquals(4, balls.get(3).getSize());
        assertEquals(6, balls.get(4).getSize());
        assertEquals(9, balls.get(5).getSize());
    }

    @Test
    public void testSortByPrice() {
        sortingAlgorithm.sort(balls, BallComparator.byPrice());
        assertEquals(new BigDecimal("1.36"), balls.get(0).getPrice());
        assertEquals(new BigDecimal("7.98"), balls.get(1).getPrice());
        assertEquals(new BigDecimal("10.54"), balls.get(2).getPrice());
        assertEquals(new BigDecimal("15.64"), balls.get(3).getPrice());
        assertEquals(new BigDecimal("22.55"), balls.get(4).getPrice());
        assertEquals(new BigDecimal("30.56"), balls.get(5).getPrice());
    }
}