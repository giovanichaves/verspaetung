package com.mobimeo.verspaetung.datasource.csv;

public class StopNotFoundException extends RuntimeException {
    public StopNotFoundException(String message) {
        super(message);
    }
}
