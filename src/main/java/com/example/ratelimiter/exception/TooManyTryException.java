package com.example.ratelimiter.exception;

public class TooManyTryException extends RuntimeException {
    public TooManyTryException(String message) {
        super(message);
    }
}
