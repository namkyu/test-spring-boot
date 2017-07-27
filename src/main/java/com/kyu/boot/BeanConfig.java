package com.kyu.boot;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class BeanConfig {

    @Bean
    public String testBean() {
        return "testBean";
    }
}
