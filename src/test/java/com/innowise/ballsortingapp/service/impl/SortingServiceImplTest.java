package com.innowise.ballsortingapp.service.impl;

import com.innowise.ballsortingapp.entity.*;
import com.innowise.ballsortingapp.exception.ServiceException;
import com.innowise.ballsortingapp.service.Sorter;
import com.innowise.ballsortingapp.service.SortingService;
import com.innowise.ballsortingapp.util.BallComparator;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Log4j2
public class SortingServiceImplTest {

    private Basket basket;
    private Basket expected;
    private static Sorter mergeSort;
    private static Sorter quickSort;
    private static SortingService sortingService;

    @BeforeAll
    static void setSorters() {
        mergeSort = MergeSort.getInstance();
        quickSort = QuickSort.getInstance();
        sortingService = SortingServiceImpl.getInstance();
    }

    @BeforeEach
    void setUp() {
        List<Ball> balls = Arrays.asList(
                new Ball(Type.BASEBALL, Producer.MIKASA, Color.GREEN, 1, new BigDecimal("1.36")),
                new Ball(Type.FOOTBALL, Producer.MOLTEN, Color.PURPLE, 4, new BigDecimal("10.54")),
                new Ball(Type.BASEBALL, Producer.SELECT, Color.GREEN, 2, new BigDecimal("30.56")),
                new Ball(Type.VOLLEYBALL, Producer.JOGEL, Color.ORANGE, 6, new BigDecimal("7.98")),
                new Ball(Type.HANDBALL, Producer.WILSON, Color.RED, 3, new BigDecimal("15.64")),
                new Ball(Type.BASKETBALL, Producer.MOLTEN, Color.PURPLE, 9, new BigDecimal("22.55"))
        );
        basket = new Basket(balls);
        expected = new Basket(balls);
    }

    @Test
    void testMergeSortBySize() {
        try {
            sortingService.sort(basket, BallComparator.bySize(), mergeSort);
            expected.getBalls().sort(BallComparator.bySize());
            assertEquals(basket.getBalls().stream().map(Ball::getSize).collect(Collectors.toList()),
                    expected.getBalls().stream().map(Ball::getSize).collect(Collectors.toList()));
        } catch (ServiceException e) {
            log.error(e.getMessage());
        }
    }

    @Test
    void testQuickSortByColor() {
        try {
            sortingService.sort(basket, BallComparator.byColor(), quickSort);
            expected.getBalls().sort(BallComparator.byColor());
            assertEquals(basket.getBalls().stream().map(Ball::getColor).collect(Collectors.toList()),
                    expected.getBalls().stream().map(Ball::getColor).collect(Collectors.toList()));
        } catch (ServiceException e) {
            log.error(e.getMessage());
        }
    }

    @Test
    void testBasketNotSpecified() {
        ServiceException serviceException = assertThrows(ServiceException.class,
                () -> sortingService.sort(null, BallComparator.byColor(), quickSort));

        assertEquals("basket not specified", serviceException.getMessage());
    }

    @Test
    void testParameterNotSpecified() {
        ServiceException serviceException = assertThrows(ServiceException.class,
                () -> sortingService.sort(basket, null, quickSort));

        assertEquals("sorting parameter not specified", serviceException.getMessage());
    }

    @Test
    void testAlgorithmNotSpecified() {
        ServiceException serviceException = assertThrows(ServiceException.class,
                () -> sortingService.sort(basket, BallComparator.byColor(), null));

        assertEquals("sorting algorithm not specified", serviceException.getMessage());
    }
}