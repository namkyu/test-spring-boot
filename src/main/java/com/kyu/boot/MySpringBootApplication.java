package com.kyu.boot;

import com.kyu.boot.interceptor.LoggingInterceptor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

//@EnableAdminServer
@SpringBootApplication
public class MySpringBootApplication {

    @Bean
    CommandLineRunner dummyCLR() {
        return args -> {
            System.out.println(args);
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(MySpringBootApplication.class, args);
    }

    @Bean
    public WebMvcConfigurerAdapter webMvcConfigurerAdapter() {
        return new WebMvcConfigurerAdapter() {

            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                registry.addInterceptor(new LoggingInterceptor())
                        .addPathPatterns("/hello")
                        .addPathPatterns("/thymleafTest");
            }
        };
    }
}
