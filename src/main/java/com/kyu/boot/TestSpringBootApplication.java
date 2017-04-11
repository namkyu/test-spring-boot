package com.kyu.boot;

import com.kyu.boot.entity.Book;
import com.kyu.boot.entity.BookDetail;
import com.kyu.boot.entity.NamkyuUser;
import com.kyu.boot.repository.BookRepository;
import com.kyu.boot.repository.HelloRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@SpringBootApplication
public class TestSpringBootApplication {

	@Bean
	CommandLineRunner dummyCLR(HelloRepository helloRepository, BookRepository bookRepository) {
        return args -> {
            Stream.of("nklee", "namkyu2", "namkyu4", "namkyu4").forEach(name -> helloRepository.save(new NamkyuUser(name)));
            helloRepository.findAll().forEach(System.out::println);

            List<Book> books = new ArrayList<>();
            books.add(new Book("Book A", new BookDetail(49)));
            books.add(new Book("Book B", new BookDetail(50)));
            books.add(new Book("Book C", new BookDetail(51)));
            bookRepository.save(books);
            bookRepository.findAll().forEach(System.out::println);
        };
    }


    /**
     *
     * @param args
     */
    public static void main(String[] args) {
		SpringApplication.run(TestSpringBootApplication.class, args);
	}
}
