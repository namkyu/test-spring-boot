package com.kyu.boot.repository;

import com.kyu.boot.entity.onetomany.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.scheduling.annotation.Async;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * @Project : test_project
 * @Date : 2017-04-11
 * @Author : nklee
 * @Description :
 */
public interface MemberRepository extends JpaRepository<Member, Integer> {


    Member findBySeqAndName(int seq, String name);

    List<Member> findTop3ByNameLike(String name);

    Member readBySeq(int seq);

    Member queryBySeq(int seq);

    long countByNameLike(String name);

    long countByName(String name);

    @Query(value = "SELECT seq, name FROM Member", nativeQuery = true)
    List<Member> nativeQuery();

    @Query(value = "SELECT seq, name FROM Member WHERE seq = :seq", nativeQuery = true)
    Member nativeQueryBySeq(@Param(value = "seq") int seq);

    @Async
    CompletableFuture<List<Member>> findAllBy();
}
