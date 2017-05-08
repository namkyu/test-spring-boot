package com.kyu.boot.jpa.mapping;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.*;
import javax.transaction.Transactional;

import static org.hamcrest.CoreMatchers.*;
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
public class OneWayTest {

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    @Transactional
    public void testOneToOne() {
        Person person = new Person();
        person.setName("nklee");

        Department department = new Department();
        department.setName("development");
        person.setDepartment(department);

        entityManager.persist(person);
        entityManager.persist(department);

        Person resultPerson = entityManager.find(Person.class, 1L);
        assertThat(resultPerson.getDepartment(), is(notNullValue()));
    }

    @Test
    @Transactional
    public void testManyToOne() {
        Team team = new Team();
        team.setName("NTeam");
        entityManager.persist(team);

        Member member = new Member();
        member.setName("nklee");
        member.setTeam(team);
        entityManager.persist(member);

        Member resultMember = entityManager.find(Member.class, 1L);
        assertThat("NTeam", is(resultMember.getTeam().getName()));

        // 수정 테스트
        Team d2 = new Team();
        d2.setName("onlineTeam");
        entityManager.persist(d2);

        Member resultPerson2 = entityManager.find(Member.class, 1L);
        resultPerson2.setTeam(d2);
        assertThat("onlineTeam", is(resultPerson2.getTeam().getName()));

        // 연관된 엔티티 삭제
        entityManager.remove(d2);
        Team resultD2 = entityManager.find(Team.class, 2L);
        assertThat(resultD2, is(nullValue()));
    }

    // ==========================================
    // 1:1 관계
    // ==========================================
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

    @Data
    @Entity
    private class Department {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private long id;

        private String name;
    }

    // ==========================================
    // 1:N 관계
    // ==========================================
    @Data
    @Entity
    private class Member {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private long id;

        private String name;

        /**
         * @JoinColumn 외래 키를 매핑할 때 사용. name 속성에는 외래 키 이름을 지정한다.
         * @JoinColumn 생략하면 외래 키를 찾을 때 기본 전략을 사용한다.
         * - 기본전략 : 필드명 + _ + 참조하는 테이블의 컬럼명 ex) departmentManyToOne_ID
         * optional 값을 false 로 설정하면 연관된 엔티티가 항상 있어야 한다.
         */
        @ManyToOne(optional = false, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
        @JoinColumn(name = "TEAM_ID")
        private Team team;
    }

    @Data
    @Entity
    private class Team {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "TEAM_ID")
        private long id;

        private String name;
    }
}
