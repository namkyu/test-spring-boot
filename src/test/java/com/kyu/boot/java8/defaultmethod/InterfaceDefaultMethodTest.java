package com.kyu.boot.java8.defaultmethod;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @Project : test_project
 * @Date : 2017-07-18
 * @Author : nklee
 * @Description :
 */
public class InterfaceDefaultMethodTest {

    @Test
    public void 디폴트메서드() {
        Namkyu namkyu = new Namkyu();
        assertThat("nklee", is(namkyu.name()));
        assertThat(36, is(namkyu.age()));
        assertThat("lee", is(namkyu.defaultName()));
    }
}


interface User {

    String name();

    default String defaultName() {
        return "lee";
    }

    default int age() {
        return 36;
    }
}

interface User2 {

    default String defaultName() {
        return "lee2";
    }

}

class Namkyu implements User, User2 {

    @Override
    public String name() {
        return "nklee";
    }

    @Override
    public String defaultName() {
        return User.super.defaultName();
    }
}