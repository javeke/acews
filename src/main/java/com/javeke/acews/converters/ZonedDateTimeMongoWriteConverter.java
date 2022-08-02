package com.javeke.acews.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;

import java.time.ZonedDateTime;
import java.util.Date;

@WritingConverter
public class ZonedDateTimeMongoWriteConverter implements Converter<ZonedDateTime, Date> {

    @Override
    public Date convert(ZonedDateTime source) {
        return Date.from(source.toInstant());
    }
}
