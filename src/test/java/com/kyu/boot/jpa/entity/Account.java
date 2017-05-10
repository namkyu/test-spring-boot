package com.kyu.boot.jpa.entity;

import com.kyu.boot.jpa.converter.attribute.LocalDateAttributeConverter;
import com.kyu.boot.jpa.converter.attribute.LocalDateTimeAttributeConverter;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @Project : test_project
 * @Date : 2017-05-10
 * @Author : nklee
 * @Description :
 */
@Data
@Entity
public class Account {

    @Id
    private int accountId;

    private String accountName;

    @Convert(converter = LocalDateAttributeConverter.class)
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDate changed;

    @Convert(converter = LocalDateTimeAttributeConverter.class)
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime changed1;

    @Temporal(TemporalType.TIMESTAMP) // 추가 안하면 오류 발생
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date changed2;
}
