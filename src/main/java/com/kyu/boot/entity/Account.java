package com.kyu.boot.entity;

import com.kyu.boot.entity.converter.LocalDateAttributeConverter;
import com.kyu.boot.entity.converter.LocalDateTimeAttributeConverter;
import lombok.Data;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @Project : test_project
 * @Date : 2017-04-11
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
    private LocalDate changed;

    @Convert(converter = LocalDateTimeAttributeConverter.class)
    private LocalDateTime changed1;

    public Account() {
    }
}
