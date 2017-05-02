package com.kyu.boot.jpa;

import com.kyu.boot.entity.onetomany.Member;
import com.kyu.boot.entity.onetomany.Phone;
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
public class SpringDataJPATest {

    @Autowired
    private MemberRepository memberRepository;

    @Test
    @Transactional
    public void oneToMany() {
        Member member1 = new Member("Lee");
        member1.addPhone(new Phone("010-1111-1111"));
        member1.addPhone(new Phone("010-2222-2222"));

        Member member2 = new Member("Jang");
        member2.addPhone(new Phone("010-3333-3333"));

        Member member3 = new Member("JUN");

        Member member4 = new Member("Lee");
        member4.addPhone(new Phone("010-1111-1111"));

        // Spring Data JPA 방식
        memberRepository.save(member1);
        memberRepository.save(member2);
        memberRepository.save(member3);

        memberRepository.findAll().forEach(System.out::println);

        System.out.println("=====================================");
        System.out.println(memberRepository.findOne(2));
        System.out.println("=====================================");
        System.out.println(memberRepository.findBySeqAndName(1, "Lee"));
    }


    @Data
    @Entity
    private class Department {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private long id;

        private String name;

        public Department() {
        }
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

        public Person() {
        }
    }

}



