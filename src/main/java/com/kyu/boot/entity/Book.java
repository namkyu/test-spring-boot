package com.kyu.boot.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @Project : test_project
 * @Date : 2017-04-11
 * @Author : nklee
 * @Description :
 */
@Data
@Entity
public class Book {

    // @Id : declares the identifier property of the entity.
    @Id
    @GeneratedValue
    private int id;
    private String name;

    // @OneToOne : defines a one-to-one relationship with another entity.
    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "id")
    private BookDetail bookDetail;

    public Book() {
    }

    public Book(String name, BookDetail bookDetail){
        this.name = name;
        this.bookDetail = bookDetail;
    }
}
