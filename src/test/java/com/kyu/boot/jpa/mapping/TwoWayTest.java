package com.kyu.boot.jpa.mapping;

import com.kyu.boot.entity.twoway.Member4;
import com.kyu.boot.entity.twoway.Team4;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Collection;

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
public class TwoWayTest {

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * 연관관계의 주인만데이터베이스 연관관계와 매핑되고외래 키를 관리할 수 있다.
     * 주인이 아닌 반대편(inverse) 은 읽기만 가능하고 외래키를 변경하지는 못한다.
     * <p>
     * 객체 관점에서 양쪽 방향에 모두 값을 입력해주는 것이 가장 안전하다.
     */
    @Test
    @Transactional
    public void test양방향() {
        Team4 team = new Team4();
        team.setName("NTeam");
        entityManager.persist(team);

        Member4 p1 = new Member4();
        p1.setName("nklee1");
        p1.setTeam(team); // 연관관계 설정 nklee1 -> NTeam
        entityManager.persist(p1);

        Member4 p2 = new Member4();
        p2.setName("nklee2");
        p2.setTeam(team); // 연관관계 설정 nklee2 -> NTeam
        entityManager.persist(p2);

        Member4 resultPerson1 = entityManager.find(Member4.class, 1L);
        Member4 resultPerson2 = entityManager.find(Member4.class, 2L);
        assertThat(resultPerson1, is(not(sameInstance(resultPerson2))));
        assertThat(resultPerson1.getTeam(), is(notNullValue()));
        assertThat(resultPerson2.getTeam(), is(notNullValue()));

        Team4 resultTeam = entityManager.find(Team4.class, 1L);
        Collection<Member4> memberList = resultTeam.getMembers();
        assertThat(2, is(memberList.size()));
    }
}


