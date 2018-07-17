package com.mobimeo.verspaetung.api.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;

import java.time.LocalTime;
import java.time.format.DateTimeParseException;

public class StringToLocalTimeConverter implements Converter<String, LocalTime> {

    @Nullable
    @Override
    public LocalTime convert(String source) {
        try {
            return LocalTime.parse(source);
        } catch (DateTimeParseException e) {
            throw new LocalTimeConversionException(e);
        }
    }
}
