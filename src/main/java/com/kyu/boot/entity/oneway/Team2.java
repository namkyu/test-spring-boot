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
public class Team2 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TEAM_ID")
    private long id;

    private String name;
}
