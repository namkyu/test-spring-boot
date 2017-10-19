package com.kyu.boot.autoconfig;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

/**
 * @Project : test-spring-boot
 * @Date : 2017-10-18
 * @Author : nklee
 * @Description :
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class AutoConfigurationTest {

    @Autowired
    private UserDAO userDAO;

    @Resource(name = "keyboard")
    private String keyboard;

    @Autowired(required = false)
    @Qualifier(value = "myDataSource")
    private String myDataSource;

    @Test
    public void 테스트_Conditional() {
        System.out.println(userDAO.getAllUserNames());
    }

    @Test
    public void 테스트_ConditionalOnMissingBean() {
        assertThat("LG", is(keyboard));
    }

    @Test
    public void 테스트_ConditionalOnProperty() {
        assertThat("local", is(myDataSource));
    }

}