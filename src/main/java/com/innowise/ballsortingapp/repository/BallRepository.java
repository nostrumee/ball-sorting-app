package com.innowise.ballsortingapp.repository;

import com.innowise.ballsortingapp.entity.Ball;
import com.innowise.ballsortingapp.exception.RepositoryException;

import java.util.List;
import java.util.Optional;

public interface BallRepository {

    List<Ball> findAll() throws RepositoryException;

}
