package com.kyu.boot.repository;

import com.kyu.boot.entity.Account;
import com.kyu.boot.entity.onetomany.Member;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Project : test_project
 * @Date : 2017-04-11
 * @Author : nklee
 * @Description :
 */
public interface MemberRepository extends JpaRepository<Member, Integer> {

    /**
     * 사용자 정의
     * 메서드 이름으로 쿼리하고 싶을 때 사용
     * @param seq
     * @param name
     * @return
     */
    Member findBySeqAndName(int seq, String name);
}
