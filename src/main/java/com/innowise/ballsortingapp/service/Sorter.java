package com.innowise.ballsortingapp.service;

import java.util.Comparator;
import java.util.List;

public interface Sorter {

    <T> void sort(List<T> elements, Comparator<T> comparator);

}
