package com.innowise.ballsortingapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Basket {

    private List<Ball> balls;
}