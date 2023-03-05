package com.innowise.ballsortingapp.service;

public class BallIdGenerator {

    private static long id = 1;

    public static long getId() {
        return id++;
    }
}