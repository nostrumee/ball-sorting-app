package com.innowise.ballsortingapp.service;

import com.innowise.ballsortingapp.entity.Basket;

public interface SortingService {

    void sort(Basket basket, BallComparator ballComparator, SortingAlgorithm sortingAlgorithm);

}
