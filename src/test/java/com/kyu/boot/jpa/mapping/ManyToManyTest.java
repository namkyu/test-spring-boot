package com.kyu.boot.jpa.mapping;

import com.kyu.boot.entity.manytomany.Member1;
import com.kyu.boot.entity.manytomany.Product1;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @Project : test_project
 * @Date : 2017-05-08
 * @Author : nklee
 * @Description :
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ManyToManyTest {

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    public void testManyToMany() {
        Product1 product = new Product1();
        product.setProductId("ProductA");
        product.setName("상품A");
        entityManager.persist(product);

        Product1 product2 = new Product1();
        product2.setProductId("ProductB");
        product2.setName("상품A");
        entityManager.persist(product2);

        Member1 member = new Member1();
        member.setMemberId("member1");
        member.setUserName("회원1");
        member.addProduct(product);
        member.addProduct(product2);
        entityManager.persist(member);

        Member1 resultMember = entityManager.find(Member1.class, "member1");
        List<Product1> productList = resultMember.getProduct();
        assertThat(productList.size(), is(2));
    }
}

