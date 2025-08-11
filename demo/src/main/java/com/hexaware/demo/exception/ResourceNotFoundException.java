package com.hexaware.demo.exception;

import org.springframework.http.HttpStatus;

public class ResourceNotFoundException extends RuntimeException {
	
    public ResourceNotFoundException(HttpStatus status, String message) {
        super(message);

    }

}
