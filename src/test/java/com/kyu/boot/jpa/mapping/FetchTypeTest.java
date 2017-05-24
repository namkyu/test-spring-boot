package com.kyu.boot.jpa.mapping;


import com.kyu.boot.entity.Account;
import com.kyu.boot.entity.AccountRole;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * @Project : test_project
 * @Date : 2017-05-08
 * @Author : nklee
 * @Description :
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class FetchTypeTest {

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    public void testProxy() {
        // em.find 방법과 같이 조회하면 엔티티를 실제 사용하든 사용하지 않든 데이터베이스를 조회한다.
        // em.getReference 방법을 이용하면 실제 엔티티를 사용하는 시점까지 데이터베이스 조회를 미룰 수 있다.
        Account account = entityManager.getReference(Account.class, 1);
        System.out.println("accountProxy : " + account.getClass().getName());

        // 프록시 인스턴스의 초기화 여부 확인 가능
        boolean isLoad = entityManager.getEntityManagerFactory().getPersistenceUnitUtil().isLoaded(account);
    }

    @Test
    public void testLazyLoading() {
        AccountRole accountRole = entityManager.find(AccountRole.class, 1);
        Account account = accountRole.getAccount();

        assertThat(false, is(entityManager.getEntityManagerFactory().getPersistenceUnitUtil().isLoaded(account)));
        System.out.println("before lazy loading!!");
        account.getAccountId();
        System.out.println("after lazy loading!!");
        assertThat(true, is(entityManager.getEntityManagerFactory().getPersistenceUnitUtil().isLoaded(account)));
    }

    @Test
    public void testLazy() {
        Account account = entityManager.find(Account.class, 1);
        List<AccountRole> list = account.getAccountRoles();
        for (AccountRole accountRole : list) {
            boolean isLoaded = entityManager.getEntityManagerFactory().getPersistenceUnitUtil().isLoaded(accountRole);
            System.out.println("class : " + accountRole.getClass().getName() + ", isLoaded : " + isLoaded);
        }

    }

}


