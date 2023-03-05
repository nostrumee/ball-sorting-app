package com.innowise.ballsortingapp.entity;

import com.innowise.ballsortingapp.service.BallIdGenerator;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class Ball {
    private long id;
    private Type type;
    private Producer producer;
    private Color color;
    private int size;
    private BigDecimal price;

    public Ball(Type type, Producer producer, Color color, int size, BigDecimal price) {
        id = BallIdGenerator.getId();
        this.type = type;
        this.producer = producer;
        this.color = color;
        this.size = size;
        this.price = price;
    }
}
