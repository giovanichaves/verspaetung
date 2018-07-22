package com.mobimeo.verspaetung.datasource.csv;

public class LineNotFoundException extends RuntimeException {
    public LineNotFoundException(String message) {
        super(message);
    }
}
