package com.kyu.boot;

import com.kyu.boot.entity.NamkyuUser;
import com.kyu.boot.interceptor.LoggingInterceptor;
import com.kyu.boot.repository.HelloRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.stream.Stream;

//@EnableAdminServer
@SpringBootApplication
public class MySpringBootApplication {

    @Bean
    CommandLineRunner dummyCLR(HelloRepository helloRepository) {
        return args -> {
            Stream.of("nklee", "namkyu2", "namkyu4", "namkyu4").forEach(name -> helloRepository.save(new NamkyuUser(name)));
            helloRepository.findAll().forEach(System.out::println);
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
                        .addPathPatterns("/convertJodaTimefromDateType")
                        .addPathPatterns("/hello")
                        .addPathPatterns("/thymleafTest");
            }
        };
    }
}
