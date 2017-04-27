package com.kyu.boot.entity.converter;

import com.kyu.boot.entity.Account;
import com.kyu.boot.repository.AccountRepository;
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
 * @Date : 2017-04-27
 * @Author : nklee
 * @Description :
 */
@Slf4j
@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest
public class ConverterTest {

    @Autowired
    private AccountRepository accountRepository;

    @Test
    public void test() {
        List<Account> list = accountRepository.findAll();
        list.forEach(System.out::println);
    }

}