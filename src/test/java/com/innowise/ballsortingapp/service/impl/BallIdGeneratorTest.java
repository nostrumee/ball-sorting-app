package com.innowise.ballsortingapp.service;

import com.innowise.ballsortingapp.exception.RepositoryException;
import com.innowise.ballsortingapp.repository.BallRepository;
import com.innowise.ballsortingapp.repository.impl.BallRepositoryImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BallIdGeneratorTest {

    private static BallRepository ballRepository;

    @BeforeAll
    public static void setUp() {
        ballRepository = BallRepositoryImpl.getInstance();
    }

    @Test
    public void testGetId() {
        try {
            long result = BallIdGenerator.getId();
            long expected = ballRepository.findAll().size() + 1;
            assertEquals(expected, result);
            result = BallIdGenerator.getId();
            assertEquals(expected + 1, result);
        } catch (RepositoryException e) {
            throw new RuntimeException(e);
        }
    }
}