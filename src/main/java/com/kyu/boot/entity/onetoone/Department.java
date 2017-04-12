package com.kyu.boot.entity.onetoone;

import lombok.Data;

import javax.persistence.*;

/**
 * @Project : test_project
 * @Date : 2017-04-12
 * @Author : nklee
 * @Description :
 */
@Data
@Entity
public class Department {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    private String name;

    public Department() {
    }
}
