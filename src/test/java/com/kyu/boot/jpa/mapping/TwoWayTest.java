package com.kyu.boot.jpa.mapping;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.ArrayList;
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
        Team team = new Team();
        team.setName("NTeam");
        entityManager.persist(team);

        Member p1 = new Member();
        p1.setName("nklee1");
        p1.setTeam(team); // 연관관계 설정 nklee1 -> NTeam
        entityManager.persist(p1);

        Member p2 = new Member();
        p2.setName("nklee2");
        p2.setTeam(team); // 연관관계 설정 nklee2 -> NTeam
        entityManager.persist(p2);

        Member resultPerson1 = entityManager.find(Member.class, 1L);
        Member resultPerson2 = entityManager.find(Member.class, 2L);
        assertThat(resultPerson1, is(not(sameInstance(resultPerson2))));
        assertThat(resultPerson1.getTeam(), is(notNullValue()));
        assertThat(resultPerson2.getTeam(), is(notNullValue()));

        Team resultTeam = entityManager.find(Team.class, 1L);
        Collection<Member> memberList = resultTeam.getMembers();
        assertThat(2, is(memberList.size()));
    }

    // ==========================================
    // N:1 관계
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

        public void setTeam(Team team) {
            if (this.team != null) {
                team.getMembers().remove(this); // 기존 팀과 관계를 제거
            }

            team.addMember(this);
            this.team = team;
        }
    }

    @Data
    @Entity
    private class Team {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "TEAM_ID")
        private long id;

        private String name;

        /**
         * 팀과 회원은 일대다 관계
         * mappedBy 속성은 양방향 매핑일 때 사용하는데 반대쪽 매핑의 필드 이름을 값으로 주면 된다.
         */
        @OneToMany(fetch = FetchType.EAGER, mappedBy = "team")
        private Collection<Member> members = new ArrayList<>();

        public void addMember(Member member) {
            this.members.add(member);
        }
    }
}
