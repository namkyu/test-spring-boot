package com.kyu.boot.jpa.mapping;

import com.kyu.boot.entity.oneway.Locker2;
import com.kyu.boot.entity.oneway.Member2;
import com.kyu.boot.entity.oneway.Person2;
import com.kyu.boot.entity.oneway.Team2;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
        Person2 person = new Person2();
        person.setName("nklee");

        Locker2 locker = new Locker2();
        locker.setName("nkLocker");
        person.setLocker(locker);

        entityManager.persist(person);
        entityManager.persist(locker);

        Person2 resultPerson = entityManager.find(Person2.class, 1L);
        assertThat(resultPerson.getLocker(), is(notNullValue()));
    }

    @Test
    @Transactional
    public void testManyToOne() {
        Team2 team = new Team2();
        team.setName("NTeam");
        entityManager.persist(team);

        Member2 member = new Member2();
        member.setName("nklee");
        member.setTeam(team);
        entityManager.persist(member);

        Member2 resultMember = entityManager.find(Member2.class, 1L);
        assertThat("NTeam", is(resultMember.getTeam().getName()));

        // 수정 테스트
        Team2 d2 = new Team2();
        d2.setName("onlineTeam");
        entityManager.persist(d2);

        Member2 resultPerson2 = entityManager.find(Member2.class, 1L);
        resultPerson2.setTeam(d2);
        assertThat("onlineTeam", is(resultPerson2.getTeam().getName()));

        // 연관된 엔티티 삭제
        entityManager.remove(d2);
        Team2 resultD2 = entityManager.find(Team2.class, 2L);
        assertThat(resultD2, is(nullValue()));
    }


}


