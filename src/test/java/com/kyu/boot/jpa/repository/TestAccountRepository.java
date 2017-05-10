package com.kyu.boot.jpa.repository;

import com.kyu.boot.jpa.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Project : test_project
 * @Date : 2017-05-10
 * @Author : nklee
 * @Description :
 */
public interface TestAccountRepository extends JpaRepository<Account, Integer> {
}