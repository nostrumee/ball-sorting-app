package com.innowise.ballsortingapp.service.impl;

import com.innowise.ballsortingapp.exception.RepositoryException;
import com.innowise.ballsortingapp.repository.BallRepository;
import com.innowise.ballsortingapp.repository.impl.BallRepositoryImpl;
import com.innowise.ballsortingapp.service.IdGenerator;
import com.innowise.ballsortingapp.service.impl.BallIdGenerator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BallIdGeneratorTest {

    private static BallRepository ballRepository;
    private static IdGenerator ballIdGenerator;

    @BeforeAll
    public static void setUp() {
        ballRepository = BallRepositoryImpl.getInstance();
        ballIdGenerator = BallIdGenerator.getInstance();
    }

    @Test
    public void testGetId() {
        try {
            long result = ballIdGenerator.getId();
            long expected = ballRepository.findAll().size() + 1;
            assertEquals(expected, result);
            result = ballIdGenerator.getId();
            assertEquals(expected + 1, result);
        } catch (RepositoryException e) {
            throw new RuntimeException(e);
        }
    }
}