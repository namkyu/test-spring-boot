package com.kyu.boot.model;

import lombok.Data;

import java.util.Date;

/**
 * @Project : test_project
 * @Date : 2017-04-25
 * @Author : nklee
 * @Description :
 */
@Data
public class Product {


    private String description;
    private Integer price;
    private Date availableFrom;

    public Product(final String description, final Integer price, final Date availableFrom) {
        this.description = description;
        this.price = price;
        this.availableFrom = availableFrom;
    }

}
