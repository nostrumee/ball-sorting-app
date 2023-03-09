package com.innowise.ballsortingapp.repository.impl;

import com.innowise.ballsortingapp.entity.Ball;
import com.innowise.ballsortingapp.exception.RepositoryException;
import com.innowise.ballsortingapp.repository.BallRepository;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;

@Log4j2
public class BallRepositoryImplTest {

    private BallRepository ballRepository;

    @BeforeEach
    void setUp() {
        ballRepository = BallRepositoryImpl.getInstance();
    }

    @Test
    void testFindAll() {
        try {
            List<Ball> balls = ballRepository.findAll();
            assertThat(balls.size(), greaterThanOrEqualTo(1));
        } catch (RepositoryException e) {
            log.error(e.getMessage());
        }
    }
}