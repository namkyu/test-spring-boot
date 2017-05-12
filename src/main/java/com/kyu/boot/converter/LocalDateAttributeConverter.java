package com.kyu.boot.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

/**
 * @Project : test_project
 * @Date : 2017-04-11
 * @Author : nklee
 * @Description :
 */
//@Converter(autoApply = true)
@Converter
public class LocalDateAttributeConverter implements AttributeConverter<LocalDate, Date> {

    /**
     * @param locDate
     * @return
     */
    @Override
    public Date convertToDatabaseColumn(LocalDate locDate) {
        if (locDate == null) {
            return null;
        }
        return Date.from(locDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * @param date
     * @return
     */
    @Override
    public LocalDate convertToEntityAttribute(Date date) {
        if (date == null) {
            return null;
        }

        return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
    }
}