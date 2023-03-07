package com.innowise.ballsortingapp.service.impl;

import com.innowise.ballsortingapp.entity.Ball;
import com.innowise.ballsortingapp.exception.RepositoryException;
import com.innowise.ballsortingapp.exception.ServiceException;
import com.innowise.ballsortingapp.repository.BallRepository;
import com.innowise.ballsortingapp.repository.impl.BallRepositoryImpl;
import com.innowise.ballsortingapp.service.BallService;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.Matchers.greaterThanOrEqualTo;

public class BallServiceImplTest {

    private static BallRepository ballRepository;
    private static BallService ballService;

    @BeforeAll
    public static void setUp() {
        ballRepository = BallRepositoryImpl.getInstance();
        ballService = BallServiceImpl.getInstance(ballRepository);
    }

    @Test
    public void testGetAll() {
        try {
            List<Ball> balls = ballService.getAll();
            MatcherAssert.assertThat(balls.size(), greaterThanOrEqualTo(1));
        } catch (ServiceException e) {
            Assertions.assertEquals("can't get any balls from repository", e.getMessage());
        }
    }

}
