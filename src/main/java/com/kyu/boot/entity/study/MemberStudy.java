package com.kyu.boot.entity.study;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Project : test_project
 * @Date : 2017-04-28
 * @Author : nklee
 * @Description :
 */

@Data
@Entity
@Table(name = "MEMBER_STUDY")
public class MemberStudy {
    @Id
    @Column(name = "ID")
    private String id;

    @Column(name = "NAME")
    private String username;

    private Integer age;
}
