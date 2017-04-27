package com.kyu.boot.entity.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @Project : test_project
 * @Date : 2017-04-11
 * @Author : nklee
 * @Description :
 */
@Converter
public class OffsetDateTimeConverter implements AttributeConverter<OffsetDateTime, String> {

    private static DateTimeFormatter FORMATTER_FROM_DB = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.nnnnnnn xxx");
    private static DateTimeFormatter FORMATTER_TO_DB = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.nnnnnnnnn xxx");

    @Override
    public String convertToDatabaseColumn(OffsetDateTime offsetDateTime) {
        if (offsetDateTime == null) {
            return null;
        }

        return offsetDateTime.format(FORMATTER_TO_DB);
    }

    @Override
    public OffsetDateTime convertToEntityAttribute(String dbDate) {
        if (dbDate == null) {
            return null;
        }

        return OffsetDateTime.parse(dbDate, FORMATTER_FROM_DB);
    }
}