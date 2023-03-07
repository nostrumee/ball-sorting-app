package com.innowise.ballsortingapp;

import com.innowise.ballsortingapp.entity.Ball;
import com.innowise.ballsortingapp.entity.Basket;
import com.innowise.ballsortingapp.repository.BallRepository;
import com.innowise.ballsortingapp.repository.impl.BallRepositoryImpl;
import com.innowise.ballsortingapp.service.BallService;
import com.innowise.ballsortingapp.service.SortingAlgorithm;
import com.innowise.ballsortingapp.service.SortingService;
import com.innowise.ballsortingapp.service.impl.BallServiceImpl;
import com.innowise.ballsortingapp.service.impl.MergeSort;
import com.innowise.ballsortingapp.service.impl.QuickSort;
import com.innowise.ballsortingapp.service.impl.SortingServiceImpl;
import com.innowise.ballsortingapp.util.BallComparator;

import java.util.Comparator;
import java.util.List;

public class App {
    public static void main(String[] args) {
        BallRepository ballRepository = BallRepositoryImpl.getInstance();
        BallService ballService = BallServiceImpl.getInstance(ballRepository);
        List<Ball> balls = ballService.getAll();
        Basket basket = new Basket(balls);
        basket.getBalls().forEach(System.out::println);
        SortingAlgorithm mergeSort = QuickSort.getInstance();
        SortingService sortingService = SortingServiceImpl.getInstance();
        Comparator<Ball> ballComparator = BallComparator.byPrice();
        sortingService.sort(basket, ballComparator, mergeSort);
        System.out.println();
        basket.getBalls().forEach(System.out::println);
    }
}
