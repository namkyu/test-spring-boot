package com.kyu.boot.entity;

import org.joda.time.DateTime;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

/**
 * @Project : test_project
 * @Date : 2017-04-11
 * @Author : nklee
 * @Description :
 */
public class NamkyuUserTest {

    @Test
    public void jodaTest() {
        DateTime dateTime = new DateTime();
        System.out.println(dateTime);
    }

}