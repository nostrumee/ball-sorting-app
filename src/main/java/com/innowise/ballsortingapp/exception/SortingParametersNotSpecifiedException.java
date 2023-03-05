package com.innowise.ballsortingapp.exception;

public class SortingParametersNotSpecifiedException extends Exception {
    public SortingParametersNotSpecifiedException() {
        super();
    }

    public SortingParametersNotSpecifiedException(String message) {
        super(message);
    }

    public SortingParametersNotSpecifiedException(String message, Throwable cause) {
        super(message, cause);
    }

    public SortingParametersNotSpecifiedException(Throwable cause) {
        super(cause);
    }
}