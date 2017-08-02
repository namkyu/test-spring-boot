package com.kyu.boot;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class MySpringBootApplicationTests {

    @Autowired
    private String testBean;

    @Autowired
    private Environment env;

    @Value("${custom.property.name}")
    private String customPropertyName;


    @Test
    public void 빈스캔테스트() {
        assertThat("testBean", is(testBean));
    }

    @Test
    public void 커스텀_프로퍼티_테스트() {
        assertNotNull(customPropertyName);

        String name = env.getProperty("custom.property.name");
        assertThat(name, is(customPropertyName));

        String message = env.getProperty("welcome.message");
        assertThat("Hello Kyu!!", is(message));
    }

}
