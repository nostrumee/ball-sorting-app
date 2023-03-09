package com.innowise.ballsortingapp.service.impl;

import com.innowise.ballsortingapp.entity.Ball;
import com.innowise.ballsortingapp.entity.Color;
import com.innowise.ballsortingapp.entity.Producer;
import com.innowise.ballsortingapp.entity.Type;
import com.innowise.ballsortingapp.exception.ServiceException;
import com.innowise.ballsortingapp.service.Sorter;
import com.innowise.ballsortingapp.service.SortingService;
import com.innowise.ballsortingapp.util.BallComparator;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Log4j2
public class SortingServiceImplTest {

    private List<Ball> balls;
    private Sorter quickSort;
    private SortingService sortingService;

    @BeforeEach
    void setUp() {
        quickSort = QuickSort.getInstance();
        sortingService = SortingServiceImpl.getInstance();
        balls = Arrays.asList(
                new Ball(Type.BASEBALL, Producer.MIKASA, Color.GREEN, 1, new BigDecimal("1.36")),
                new Ball(Type.FOOTBALL, Producer.MOLTEN, Color.PURPLE, 4, new BigDecimal("10.54")),
                new Ball(Type.BASEBALL, Producer.SELECT, Color.GREEN, 2, new BigDecimal("30.56")),
                new Ball(Type.VOLLEYBALL, Producer.JOGEL, Color.ORANGE, 6, new BigDecimal("7.98")),
                new Ball(Type.HANDBALL, Producer.WILSON, Color.RED, 3, new BigDecimal("15.64")),
                new Ball(Type.BASKETBALL, Producer.MOLTEN, Color.PURPLE, 9, new BigDecimal("22.55"))
        );
    }

    @Test
    void testBasketNotSpecified() {
        ServiceException serviceException = assertThrows(ServiceException.class,
                () -> sortingService.sort(null, BallComparator.byColor(), quickSort));
        assertEquals("elements for sorting not specified", serviceException.getMessage());
    }

    @Test
    void testParameterNotSpecified() {
        ServiceException serviceException = assertThrows(ServiceException.class,
                () -> sortingService.sort(balls, null, quickSort));
        assertEquals("comparator not specified", serviceException.getMessage());
    }

    @Test
    void testAlgorithmNotSpecified() {
        ServiceException serviceException = assertThrows(ServiceException.class,
                () -> sortingService.sort(balls, BallComparator.byColor(), null));
        assertEquals("sorter not specified", serviceException.getMessage());
    }
}