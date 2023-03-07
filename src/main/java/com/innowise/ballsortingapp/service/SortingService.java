package com.innowise.ballsortingapp.service;

import com.innowise.ballsortingapp.entity.Ball;
import com.innowise.ballsortingapp.entity.Basket;
import com.innowise.ballsortingapp.exception.ServiceException;

import java.util.Comparator;

public interface SortingService {

    void sort(Basket basket, Comparator<Ball> ballComparator, SortingAlgorithm sortingAlgorithm) throws ServiceException;

}
