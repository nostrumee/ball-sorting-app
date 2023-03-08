package com.innowise.ballsortingapp.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class Basket {

    private List<Ball> balls;

    public Basket(List<Ball> balls) {
        this.balls = new ArrayList<>(balls);
    }
}