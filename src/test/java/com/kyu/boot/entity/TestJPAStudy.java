package com.kyu.boot.entity;

import com.kyu.boot.entity.study.MemberStudy;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

/**
 * @Project : test_project
 * @Date : 2017-04-28
 * @Author : nklee
 * @Description :
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestJPAStudy {

    @PersistenceContext
    private EntityManager em;

    @Test
    @Transactional
    public void test1() {
        // set Data
        String id = "id1";
        MemberStudy member = new MemberStudy();
        member.setId(id);
        member.setUsername("남규");
        member.setAge(2);

        // 등록
        em.persist(member);

        // 수정
        member.setAge(20);

        // 한 건 조회
        MemberStudy findMember = em.find(MemberStudy.class, id);
        System.out.println("findMember=" + findMember.getUsername() + ", age=" + findMember.getAge());

        // 목록 조회
        List<MemberStudy> members = em.createQuery("select m from MemberStudy m", MemberStudy.class).getResultList();
        System.out.println("members.size=" + members.size());

        // 삭제
        em.remove(member);
    }
}
