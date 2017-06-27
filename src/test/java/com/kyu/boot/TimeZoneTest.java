package com.kyu.boot;

import org.junit.Test;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @Project : test_project
 * @Date : 2017-05-29
 * @Author : nklee
 * @Description :
 */
public class TimeZoneTest {

    @Test
    public void testTimezone() {
        String dbDate = "2017-05-10 16:26:40.931 +09:00";
        LocalDateTime ldt = LocalDateTime.parse(dbDate, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS ZZZZZ"));
        System.out.println(ldt);
    }


    @Test
    public void test2() throws ParseException {
        String dbDate = "2017-05-10 16:26:40.931 +09:00";
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS ZZZZZ");
        ZonedDateTime zdt = ZonedDateTime.parse(dbDate, dtf);
        System.out.println(zdt);
        OffsetDateTime offsetDateTime = OffsetDateTime.now();
        System.out.println(offsetDateTime);
    }

    @Test
    public void test3() {
        OffsetDateTime parseDate = OffsetDateTime.parse("2017-05-10 16:26:40.931 +09:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS ZZZZZ"));
        System.out.println("파싱 날짜 : " + parseDate);
        System.out.println("년 : " + parseDate.getYear());
        System.out.println("월 : " + parseDate.getMonthValue());
        System.out.println("일 : " + parseDate.getDayOfMonth());
        System.out.println("시 : " + parseDate.getHour());
        System.out.println("분 : " + parseDate.getMinute());
        System.out.println("초 : " + parseDate.getSecond());
        System.out.println(parseDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
    }

    @Test
    public void test4() {

        OffsetDateTime parseDate = OffsetDateTime.parse("2017-06-16", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        System.out.println(parseDate);

    }
}
