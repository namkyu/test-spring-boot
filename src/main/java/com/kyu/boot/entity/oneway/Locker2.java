package com.kyu.boot.entity.oneway;

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
@Table(name = "LOCKER")
public class Locker2 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
}
