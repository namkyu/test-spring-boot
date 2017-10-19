package com.kyu.boot.autoconfig;

import java.util.Arrays;
import java.util.List;

/**
 * @Project : test-spring-boot
 * @Date : 2017-10-18
 * @Author : nklee
 * @Description :
 */
class JdbcUserDAO implements UserDAO {

    @Override
    public List<String> getAllUserNames() {
        System.out.println("***** from RDBMS *****");
        return Arrays.asList("nklee", "nklee1", "nklee2");
    }
}
