package com.innowise.ballsortingapp.service;

import com.innowise.ballsortingapp.exception.ServiceException;

import java.util.Comparator;
import java.util.List;

public interface SortingService {

    <T> void sort(List<T> elements, Comparator<T> comparator, Sorter sorter) throws ServiceException;

}