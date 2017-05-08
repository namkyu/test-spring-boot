package com.kyu.boot.jpa;

import com.kyu.boot.repository.MemberRepository;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.*;
import javax.transaction.Transactional;


@Slf4j
@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest
public class JPQLTest {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private MemberRepository memberRepository;

    @Test
    @Transactional
    public void testJPQL() {

        Department department = new Department();
        department.setName("development");
        entityManager.persist(department);

        for (int i = 0; i < 10; i++) {
            Person person = new Person();
            person.setName("nklee" + i);
            person.setDepartment(department);
            entityManager.persist(person);
        }

        // JPQL은 객체지향 쿼리
        entityManager.createQuery("SELECT p FROM JPQLTest$Person p")
                .getResultList()
                .forEach(System.out::println);

        System.out.println("===================================================");
        entityManager.createQuery("SELECT p FROM JPQLTest$Person p")
                .setMaxResults(5)
                .getResultList()
                .forEach(System.out::println);
    }

    @Data
    @Entity
    private class Department {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private long id;

        private String name;

    }

    @Data
    @Entity
    private class Person {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private long id;

        private String name;

        @OneToOne
        @PrimaryKeyJoinColumn
        private Department department;
    }

}



