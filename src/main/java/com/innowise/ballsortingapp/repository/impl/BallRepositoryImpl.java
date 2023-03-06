package com.innowise.ballsortingapp.repository.impl;

import com.innowise.ballsortingapp.entity.Ball;
import com.innowise.ballsortingapp.entity.Color;
import com.innowise.ballsortingapp.entity.Producer;
import com.innowise.ballsortingapp.entity.Type;
import com.innowise.ballsortingapp.repository.BallRepository;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BallRepositoryImpl implements BallRepository {

    private static BallRepository instance;
    private static final Lock lock = new ReentrantLock();
    private static final AtomicBoolean created = new AtomicBoolean(false);

    private final List<Ball> balls;

    private BallRepositoryImpl() {

    }

    public static BallRepository getInstance() {
        if (!created.get()) {
            try {
                lock.lock();
                if (instance == null) {
                    instance = new BallRepositoryImpl();
                    created.set(true);
                }
            } finally {
                lock.unlock();
            }
        }

        return instance;
    }



    {
        balls = Arrays.asList(
                new Ball(Type.BASEBALL, Producer.MIKASA, Color.GREEN, 1, new BigDecimal("4.36")),
                new Ball(Type.FOOTBALL, Producer.MOLTEN, Color.PURPLE, 5, new BigDecimal("10.54")),
                new Ball(Type.BASEBALL, Producer.SELECT, Color.GREEN, 2, new BigDecimal("3.56")),
                new Ball(Type.VOLLEYBALL, Producer.JOGEL, Color.ORANGE, 6, new BigDecimal("7.98")),
                new Ball(Type.HANDBALL, Producer.WILSON, Color.RED, 3, new BigDecimal("15.64")),
                new Ball(Type.BASKETBALL, Producer.MOLTEN, Color.PURPLE, 9, new BigDecimal("22.55")),
                new Ball(Type.VOLLEYBALL, Producer.MIKASA, Color.BLUE, 5, new BigDecimal("13.58")),
                new Ball(Type.BASKETBALL, Producer.SELECT, Color.GREEN, 2, new BigDecimal("7.25")),
                new Ball(Type.VOLLEYBALL, Producer.WILSON, Color.YELLOW, 4, new BigDecimal("10.75")),
                new Ball(Type.FOOTBALL, Producer.JOGEL, Color.ORANGE, 3, new BigDecimal("15.2")),
                new Ball(Type.BASKETBALL, Producer.MIKASA, Color.RED, 8, new BigDecimal("5.25")),
                new Ball(Type.VOLLEYBALL, Producer.MOLTEN, Color.BLUE, 7, new BigDecimal("10.3")),
                new Ball(Type.BASEBALL, Producer.SELECT, Color.PURPLE, 9, new BigDecimal("5.65")),
                new Ball(Type.HANDBALL, Producer.MOLTEN, Color.GREEN, 5, new BigDecimal("22.55")),
                new Ball(Type.FOOTBALL, Producer.WILSON, Color.YELLOW, 4, new BigDecimal("15.64"))
        );
    }


    @Override
    public List<Ball> findAll() {
        return balls;
    }
}
