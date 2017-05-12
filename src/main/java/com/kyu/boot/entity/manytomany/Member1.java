package com.kyu.boot.entity.manytomany;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Project : test_project
 * @Date : 2017-05-12
 * @Author : nklee
 * @Description :
 */
@Data
@Entity
public class Member1 {

    @Id
    @Column(name = "MEMBER_ID")
    private String memberId;

    @Column(name = "USER_NAME")
    private String userName;

    // 복합키 외에 별도의 속성이 있으면 다대일 관계로 풀어야 한다. 실무에서는 ManyToMany 비추
    @ManyToMany
    @JoinTable(name = "MEMBER_PRODUCT"
            , joinColumns = @JoinColumn(name = "MEMBER_ID")
            , inverseJoinColumns = @JoinColumn(name = "PRODUCT_ID"))
    private List<Product1> product = new ArrayList<>();

    public void addProduct(Product1 product) {
        this.product.add(product);
    }
}
