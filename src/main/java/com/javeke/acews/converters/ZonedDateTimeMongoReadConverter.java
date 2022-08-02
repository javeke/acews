package com.javeke.acews.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Date;

@ReadingConverter
public class ZonedDateTimeMongoReadConverter implements Converter<Date, ZonedDateTime> {

    @Override
    public ZonedDateTime convert(Date source) {
        return source.toInstant().atZone(ZoneOffset.UTC);
    }
}
