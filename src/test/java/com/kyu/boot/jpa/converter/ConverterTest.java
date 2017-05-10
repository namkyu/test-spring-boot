package com.kyu.boot.jpa.converter;

import com.kyu.boot.jpa.entity.Account;
import com.kyu.boot.jpa.repository.TestAccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.hamcrest.core.IsSame;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.Assert.assertThat;

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
    private TestAccountRepository testAccountRepository;

    @Test
    public void testConverter() {
        List<Account> list = testAccountRepository.findAll();
        Account account = list.get(0);
        assertThat(account.getChanged().getClass(), IsSame.sameInstance(LocalDate.class));
        assertThat(account.getChanged1().getClass(), IsSame.sameInstance(LocalDateTime.class));
    }

}