package com.innowise.ballsortingapp.service.impl;

import com.innowise.ballsortingapp.entity.Ball;
import com.innowise.ballsortingapp.exception.RepositoryException;
import com.innowise.ballsortingapp.exception.ServiceException;
import com.innowise.ballsortingapp.repository.BallRepository;
import com.innowise.ballsortingapp.service.BallService;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j
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
    public List<Ball> getAll() throws ServiceException {
        try {
            return ballRepository.findAll();
        } catch (RepositoryException e) {
            log.error(e.getMessage());
            throw new ServiceException(e);
        }
    }
}