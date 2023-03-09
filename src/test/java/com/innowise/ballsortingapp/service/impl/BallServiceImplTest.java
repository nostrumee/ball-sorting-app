package com.innowise.ballsortingapp.service.impl;

import com.innowise.ballsortingapp.entity.Ball;
import com.innowise.ballsortingapp.entity.Color;
import com.innowise.ballsortingapp.entity.Producer;
import com.innowise.ballsortingapp.entity.Type;
import com.innowise.ballsortingapp.exception.RepositoryException;
import com.innowise.ballsortingapp.exception.ServiceException;
import com.innowise.ballsortingapp.repository.BallRepository;
import com.innowise.ballsortingapp.service.BallService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;

@Slf4j
public class BallServiceImplTest {

    private BallService ballService;
    private BallRepository ballRepository;
    private List<Ball> mockBalls;

    @BeforeEach
    void setUp() {
        ballRepository = Mockito.mock(BallRepository.class);
        ballService = BallServiceImpl.getInstance(ballRepository);
        mockBalls = Arrays.asList(
                new Ball(Type.BASEBALL, Producer.MIKASA, Color.GREEN, 1, new BigDecimal("1.36")),
                new Ball(Type.FOOTBALL, Producer.MOLTEN, Color.PURPLE, 4, new BigDecimal("10.54")),
                new Ball(Type.BASEBALL, Producer.SELECT, Color.GREEN, 2, new BigDecimal("30.56")),
                new Ball(Type.VOLLEYBALL, Producer.JOGEL, Color.ORANGE, 6, new BigDecimal("7.98")),
                new Ball(Type.HANDBALL, Producer.WILSON, Color.RED, 3, new BigDecimal("15.64")),
                new Ball(Type.BASKETBALL, Producer.MOLTEN, Color.PURPLE, 9, new BigDecimal("22.55"))
        );
    }

    @Test
    void testGetAll() {
        try {
            Mockito.doReturn(mockBalls).when(ballRepository).findAll();
            List<Ball> balls = ballService.getAll();
            assertThat(balls.size(), greaterThanOrEqualTo(1));
        } catch (RepositoryException | ServiceException e) {
            log.error(e.getMessage());
        }
    }
}