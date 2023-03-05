package com.innowise.ballsortingapp.service;

import com.innowise.ballsortingapp.entity.Ball;

import java.util.List;

public interface SortingService {

    void sort(List<Ball> listOfBalls, SortingAlgorithm sortingAlgorithm);

}
