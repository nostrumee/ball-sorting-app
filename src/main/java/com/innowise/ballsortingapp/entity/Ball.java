package com.innowise.ballsortingapp.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class Ball {

    private Type type;
    private Producer producer;
    private Color color;
    private int size;
    private BigDecimal price;

    public Ball(Type type, Producer producer, Color color, int size, BigDecimal price) {
        this.type = type;
        this.producer = producer;
        this.color = color;
        this.size = size;
        this.price = price;
    }
}