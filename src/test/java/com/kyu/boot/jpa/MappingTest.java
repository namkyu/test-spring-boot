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
public class MappingTest {

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    @Transactional
    public void oneToOne() {

        for (int i = 0; i < 10; i++) {
            PersonOneToOne personOneToOne = new PersonOneToOne();
            personOneToOne.setName("nklee" + i);

            DepartmentOneToOne departmentOneToOne = new DepartmentOneToOne();
            departmentOneToOne.setName("development");
            personOneToOne.setDepartmentOneToOne(departmentOneToOne);

            entityManager.persist(personOneToOne);
            entityManager.persist(departmentOneToOne);
        }

        // 동일 instance인지 확인
        PersonOneToOne resultPerson = entityManager.find(PersonOneToOne.class, 1L);
        PersonOneToOne resultPerson1 = entityManager.find(PersonOneToOne.class, 1L);
        assertThat(resultPerson, is(sameInstance(resultPerson1)));

        System.out.println(resultPerson);
    }

    @Data
    @Entity
    private class PersonOneToOne {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private long id;

        private String name;

        @OneToOne
        @PrimaryKeyJoinColumn
        private DepartmentOneToOne departmentOneToOne;
    }

    @Data
    @Entity
    private class DepartmentOneToOne {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private long id;

        private String name;
    }

    // ==========================================

    @Data
    @Entity
    private class PersonManyToOne {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private long id;

        private String name;

        @ManyToOne
        @PrimaryKeyJoinColumn
        private DepartmentOneToOne departmentOneToOne;
    }

    @Data
    @Entity
    private class DepartmentManyToOne {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private long id;

        private String name;
    }


}



