package com.kyu.boot.jpa;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.*;
import javax.transaction.Transactional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.junit.Assert.assertThat;


@Slf4j
@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest
public class PersistenceContextTest {

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    @Transactional
    public void 동일객체테스트() {
        Person person = new Person();
        person.setName("nklee");

        entityManager.persist(person);
        Person savedEntity = entityManager.find(Person.class, 1L); // 영속성 컨텍스트에서 데이터 조회
        Person savedEntity1 = entityManager.find(Person.class, 1L); // 영속성 컨텍스트에서 데이터 조회

        // 데이터베이스에서 select 하는 것이 아니라 영속성 컨텍스트에서 데이터를 조회하기 때문에 동일 객체
        assertThat(savedEntity, is(sameInstance(savedEntity1)));
    }

    @Data
    @Entity
    private class Person {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private long id;

        private String name;
    }
}


