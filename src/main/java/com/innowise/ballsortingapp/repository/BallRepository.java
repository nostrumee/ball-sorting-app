package com.innowise.ballsortingapp.repository;

import com.innowise.ballsortingapp.entity.Ball;
import com.innowise.ballsortingapp.exception.RepositoryException;

import java.util.List;

public interface BallRepository {

    List<Ball> findAll() throws RepositoryException;
}
