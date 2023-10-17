package com.example.handler;

import io.github.resilience4j.bulkhead.BulkheadFullException;
import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import io.github.resilience4j.ratelimiter.RequestNotPermitted;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class CustomExceptionHandler {

//    @ExceptionHandler({ BulkheadFullException.class })
//    @ResponseStatus(HttpStatus.BANDWIDTH_LIMIT_EXCEEDED)
//    public void handleBulkheadFullException() {
//    }
//
//    @ExceptionHandler({CallNotPermittedException.class})
//    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
//    public void handleCallNotPermittedException() {
//    }
//
//    @ExceptionHandler({RequestNotPermitted.class})
//    @ResponseStatus(HttpStatus.TOO_MANY_REQUESTS)
//    public void handleRequestNotPermitted() {
//    }

    private static class ErrResponse {
        String message;

        public ErrResponse() {
        }

        public ErrResponse(String message) {
            super();
            this.message = message;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ErrResponse> handleException(Exception e) {
        return new ResponseEntity<>(new ErrResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
    }
}