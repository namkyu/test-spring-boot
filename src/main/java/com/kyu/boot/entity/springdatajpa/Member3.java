package com.kyu.boot.entity.springdatajpa;

import com.kyu.boot.constants.Gender;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

/**
 * @Project : test_project
 * @Date : 2017-05-12
 * @Author : nklee
 * @Description :
 */
@Data
@Entity
public class Member3 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int seq;

    private String name;

    @Enumerated(EnumType.ORDINAL)
    private Gender gender;

    // default fetch type = EAGER
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, targetEntity = Phone3.class)
    private Collection<Phone3> phone;

    public Member3() {
    }

    public Member3(String name) {
        this.name = name;
    }

    public Member3(String name, Gender gender) {
        this.name = name;
        this.gender = gender;
    }

    public void addPhone(Phone3 p) {
        if (phone == null) {
            phone = new ArrayList<>();
        }

        phone.add(p);
    }
}
