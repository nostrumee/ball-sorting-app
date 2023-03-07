package com.innowise.ballsortingapp.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BallIdGeneratorTest {

    @Test
    public void testGetId() {
        long result = BallIdGenerator.getId();
        assertEquals(1, result);
        result = BallIdGenerator.getId();
        assertEquals(2, result);
    }
}
