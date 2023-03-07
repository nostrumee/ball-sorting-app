package com.innowise.ballsortingapp.repository.impl;

import com.innowise.ballsortingapp.entity.Ball;
import com.innowise.ballsortingapp.exception.RepositoryException;
import com.innowise.ballsortingapp.repository.BallRepository;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.junit.Assert.assertEquals;

public class BallRepositoryImplTest {

    private static BallRepository ballRepository;

    @BeforeAll
    public static void setUp() {
        ballRepository = BallRepositoryImpl.getInstance();
    }

    @Test
    public void findAll() {
        try {
            List<Ball> balls = ballRepository.findAll();
            MatcherAssert.assertThat(balls.size(), greaterThanOrEqualTo(1));
        } catch (RepositoryException e) {
            Assertions.assertEquals("can't get any balls from repository", e.getMessage());
        }
    }
}
