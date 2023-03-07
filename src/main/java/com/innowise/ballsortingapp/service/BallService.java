package com.innowise.ballsortingapp.service;

import com.innowise.ballsortingapp.entity.Ball;
import com.innowise.ballsortingapp.exception.ServiceException;

import java.util.List;

public interface BallService {

    List<Ball> getAll() throws ServiceException;

}
