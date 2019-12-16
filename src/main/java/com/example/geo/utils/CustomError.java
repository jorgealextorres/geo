package com.example.geo.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CustomError {
    @Value("${CustomError.Message}")
    private String message;

    public CustomError() {
    }

    public CustomError(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
