package com.mobimeo.verspaetung.api.converter;

import java.time.format.DateTimeParseException;

public class LocalTimeConversionException extends RuntimeException {
    public LocalTimeConversionException(DateTimeParseException e) {
        super(e);
    }
}
