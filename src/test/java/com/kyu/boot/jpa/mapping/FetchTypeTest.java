package com.kyu.boot.jpa.mapping;

import com.kyu.boot.jpa.entity.Account;
import com.kyu.boot.jpa.repository.AccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @Project : test_project
 * @Date : 2017-05-08
 * @Author : nklee
 * @Description :
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class FetchTypeTest {

    @Autowired
    private AccountRepository accountRepository;

    @Test
    @Transactional
    public void testLazy() {
        List<Account> accountList = accountRepository.findAll();
        for (Account account : accountList) {
            System.out.println(account);
        }
    }

}


