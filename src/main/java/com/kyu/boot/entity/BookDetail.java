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
public class BookDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Integer numberOfPages;

    @OneToOne(mappedBy = "bookDetail")
    private Book book;

    public BookDetail() {
    }

    public BookDetail(Integer id) {
        this.id = id;
    }


}
