package com.innowise.ballsortingapp.exception;

public class BallsNotFoundException extends Exception {

    public BallsNotFoundException() {
        super();
    }

    public BallsNotFoundException(String message) {
        super(message);
    }

    public BallsNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public BallsNotFoundException(Throwable cause) {
        super(cause);
    }
}