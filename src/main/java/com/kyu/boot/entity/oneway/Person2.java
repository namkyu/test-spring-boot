package com.kyu.boot.entity.oneway;

import lombok.Data;

import javax.persistence.*;

/**
 * @Project : test_project
 * @Date : 2017-05-12
 * @Author : nklee
 * @Description :
 */ // ==========================================
// 1:1 관계
// ==========================================
@Data
@Entity
@Table(name = "PERSON")
public class Person2 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @OneToOne
    @PrimaryKeyJoinColumn
    private Locker2 locker;
}
