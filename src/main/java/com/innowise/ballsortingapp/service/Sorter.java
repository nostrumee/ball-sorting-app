package com.innowise.ballsortingapp.service;

import com.innowise.ballsortingapp.entity.Ball;

import java.util.Comparator;
import java.util.List;

public interface Sorter {

    void sort(List<Ball> balls, Comparator<Ball> ballComparator);

}
