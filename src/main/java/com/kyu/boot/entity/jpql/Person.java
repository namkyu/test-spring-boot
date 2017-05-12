package com.kyu.boot.entity.jpql;

import lombok.Data;

import javax.persistence.*;

/**
 * @Project : test_project
 * @Date : 2017-05-12
 * @Author : nklee
 * @Description :
 */
@Data
@Entity
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @OneToOne
    @PrimaryKeyJoinColumn
    private Department department;
}
