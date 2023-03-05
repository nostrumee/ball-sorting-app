package com.innowise.ballsortingapp.entity;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class Ball {

    private Type type;
    private Producer producer;
    private Color color;
    private int size;
    private BigDecimal price;

}
