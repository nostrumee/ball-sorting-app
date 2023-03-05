package com.innowise.ballsortingapp.repository;

import com.innowise.ballsortingapp.entity.Ball;

import java.util.List;
import java.util.Optional;

public interface BallRepository {

    Optional<List<Ball>> findAll();

    void add(Ball ball);

}
