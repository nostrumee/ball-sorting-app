package com.innowise.ballsortingapp.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@Data
public class Basket {

    private final List<Ball> balls;

    {
        balls = Arrays.asList(
                new Ball(Type.BASEBALL, Producer.MIKASA, Color.GREEN, 1, new BigDecimal("4.36")),
                new Ball(Type.FOOTBALL, Producer.MOLTEN, Color.PURPLE, 5, new BigDecimal("10.54")),
                new Ball(Type.BASEBALL, Producer.SELECT, Color.GREEN, 2, new BigDecimal("3.56")),
                new Ball(Type.VOLLEYBALL, Producer.JOGEL, Color.ORANGE, 6, new BigDecimal("7.98")),
                new Ball(Type.HANDBALL, Producer.WILSON, Color.RED, 3, new BigDecimal("15.64")),
                new Ball(Type.FOOTBALL, Producer.MOLTEN, Color.PURPLE, 9, new BigDecimal("22.55")),
                new Ball(Type.VOLLEYBALL, Producer.MIKASA, Color.BLUE, 5, new BigDecimal("13.58")),
                new Ball(Type.HANDBALL, Producer.SELECT, Color.GREEN, 2, new BigDecimal("7.25")),
                new Ball(Type.VOLLEYBALL, Producer.WILSON, Color.YELLOW, 4, new BigDecimal("10.75")),
                new Ball(Type.FOOTBALL, Producer.JOGEL, Color.ORANGE, 3, new BigDecimal("15.2")),
                new Ball(Type.BASEBALL, Producer.MIKASA, Color.RED, 8, new BigDecimal("5.25")),
                new Ball(Type.VOLLEYBALL, Producer.MOLTEN, Color.BLUE, 7, new BigDecimal("10.3")),
                new Ball(Type.BASEBALL, Producer.SELECT, Color.PURPLE, 9, new BigDecimal("5.65")),
                new Ball(Type.HANDBALL, Producer.MOLTEN, Color.GREEN, 5, new BigDecimal("22.55")),
                new Ball(Type.FOOTBALL, Producer.WILSON, Color.YELLOW, 4, new BigDecimal("15.64"))
        );
    }

    public void addBall(Ball ball) {
        balls.add(ball);
    }

}
