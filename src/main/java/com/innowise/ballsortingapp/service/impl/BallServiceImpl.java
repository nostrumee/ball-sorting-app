package com.innowise.ballsortingapp.service.impl;

import com.innowise.ballsortingapp.entity.Ball;
import com.innowise.ballsortingapp.exception.BallsNotFoundException;
import com.innowise.ballsortingapp.repository.BallRepository;
import com.innowise.ballsortingapp.service.BallService;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BallServiceImpl  implements BallService {

    private static BallService instance;
    private static final Lock lock = new ReentrantLock();
    private static final AtomicBoolean created = new AtomicBoolean(false);

    private final BallRepository ballRepository;

    private BallServiceImpl(BallRepository ballRepository) {
        this.ballRepository = ballRepository;
    }

    public static BallService getInstance(BallRepository ballRepository) {
        if (!created.get()) {
            try {
                lock.lock();
                if (instance == null) {
                    instance = new BallServiceImpl(ballRepository);
                    created.set(true);
                }
            } finally {
                lock.unlock();
            }
        }

        return instance;
    }

    @Override
    public List<Ball> getAll() {
        try {
            List<Ball> balls = ballRepository.findAll();
            if (balls != null && !balls.isEmpty()) {
                return new ArrayList<>(balls);
            } else {
                throw new BallsNotFoundException("Balls nof found");
            }
        } catch (BallsNotFoundException e) {
            //log.error(e.getMessage())
            throw new RuntimeException(e);
        }
    }
}
