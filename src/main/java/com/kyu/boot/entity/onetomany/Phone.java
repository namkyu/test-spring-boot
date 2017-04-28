package com.kyu.boot.entity.onetomany;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @Project : test_project
 * @Date : 2017-04-12
 * @Author : nklee
 * @Description :
 */
@Data
@Entity
public class Phone {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int seq;

    private String no;

    public Phone() {
    }

    public Phone(String no) {
        this.no = no;
    }

}
