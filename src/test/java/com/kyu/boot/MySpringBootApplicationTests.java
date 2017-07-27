package com.kyu.boot;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class MySpringBootApplicationTests {

    @Autowired
    private String testBean;

    @Test
    public void 빈스캔테스트() {
        assertThat("testBean", is(testBean));

    }

}
