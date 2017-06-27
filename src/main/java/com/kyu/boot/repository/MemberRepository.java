package com.kyu.boot.repository;

import com.kyu.boot.entity.springdatajpa.Member3;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.scheduling.annotation.Async;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * @Project : test_project
 * @Date : 2017-05-12
 * @Author : nklee
 * @Description :
 */
public interface MemberRepository extends JpaRepository<Member3, Integer> {

    Member3 findBySeqAndName(int seq, String name);

    List<Member3> findTop3ByNameLike(String name);

    Member3 readBySeq(int seq);

    Member3 readByName(String name);

    Member3 queryBySeq(int seq);

    long countByNameLike(String name);

    long countByName(String name);

    @org.springframework.data.jpa.repository.Query(value = "SELECT seq, name FROM Member3", nativeQuery = true)
    List<Member3> nativeQuery();

    @org.springframework.data.jpa.repository.Query(value = "SELECT seq, name FROM Member3 WHERE name = :name", nativeQuery = true)
    Member3 nativeQueryByName(@Param(value = "name") String name);

    @Async
    CompletableFuture<List<Member3>> findAllBy();
}
