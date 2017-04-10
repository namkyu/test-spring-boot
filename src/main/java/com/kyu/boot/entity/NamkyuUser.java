package com.kyu.boot.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by nklee on 2017-04-10.
 */
@Data
@Entity
public class NamkyuUser {

    @Id
    @GeneratedValue
    private Long sequenceId;

    private String userId;

    public NamkyuUser(String userId) {
        this.userId = userId;
    }

    public NamkyuUser() {
        // need default constructor by JPA
    }
}
