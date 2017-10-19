package com.kyu.boot.autoconfig;

import java.util.Arrays;
import java.util.List;

/**
 * @Project : test-spring-boot
 * @Date : 2017-10-18
 * @Author : nklee
 * @Description :
 */
class MongoUserDAO implements UserDAO {

    @Override
    public List<String> getAllUserNames() {
        System.out.println("***** from Mongo *****");
        return Arrays.asList("nklee10", "nklee11", "nklee12");
    }
}
