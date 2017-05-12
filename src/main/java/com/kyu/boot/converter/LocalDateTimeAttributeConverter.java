package com.kyu.boot.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.time.LocalDateTime;
import java.util.Date;

import static java.time.Instant.ofEpochMilli;
import static java.time.LocalDateTime.ofInstant;
import static java.time.ZoneId.systemDefault;


/**
 * @Project : test_project
 * @Date : 2017-04-11
 * @Author : nklee
 * @Description :
 */
@Converter
public class LocalDateTimeAttributeConverter implements AttributeConverter<LocalDateTime, Date> {

    /**
     * 엔티티의 데이터를 데이터베이스 컬럼에 저장할 데이터로 변환
     *
     * @param localDateTime
     * @return
     */
    @Override
    public Date convertToDatabaseColumn(LocalDateTime localDateTime) {
        if (localDateTime == null) {
            return null;
        }
        return Date.from(localDateTime.atZone(systemDefault()).toInstant());
    }

    /**
     * 데이터베이스에서 조회한 컬럼 데이터를 엔티티의 데이터로 변환
     *
     * @param date
     * @return
     */
    @Override
    public LocalDateTime convertToEntityAttribute(Date date) {
        if (date == null) {
            return null;
        }
        return ofInstant(ofEpochMilli(date.getTime()), systemDefault());
    }
}
