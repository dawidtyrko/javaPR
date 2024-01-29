package com.dogs.projectjava.exceptionHandler;

import lombok.RequiredArgsConstructor;

import java.time.Instant;


public class ErrorResponse {
    private final Instant timeStamp;
    private final String message;

    public ErrorResponse(Instant timeStamp, String message) {
        this.timeStamp = timeStamp;
        this.message = message;
    }
}
