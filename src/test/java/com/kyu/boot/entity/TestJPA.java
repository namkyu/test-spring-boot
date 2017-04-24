package com.kyu.boot.entity;

import com.kyu.boot.entity.onetomany.Member;
import com.kyu.boot.entity.onetomany.Phone;
import com.kyu.boot.entity.onetoone.Department;
import com.kyu.boot.entity.onetoone.Person;
import com.kyu.boot.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @Project : test_project
 * @Date : 2017-04-24
 * @Author : nklee
 * @Description :
 */
@Slf4j
@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestJPA {

    public static final int CNT = 200;

    @Autowired
    private EntityManager em;

    @Autowired
    private MemberRepository memberRepository;

    @Before
    public void dummyInsertPersonData() {
        for (int i = 0; i < CNT; i++) {
            Person person = new Person();
            person.setName("nklee" + i);

            Department department = new Department();
            department.setName("development");
            person.setDepartment(department);

            em.persist(person);
            em.persist(department);
        }
    }

    @Before
    public void dummyInsertMemberData() {
        for (int i = 0; i < CNT; i++) {
            Member member = new Member("Lee" + i);
            member.addPhone(new Phone("010-1111-1111"));
            member.addPhone(new Phone("010-2222-2222"));
            memberRepository.save(member);
        }
    }

    // =================================================================

    @Test
    public void testSpringDataJPA() {
        List<Member> list = memberRepository.findAll();
        assertThat(list.size(), is(CNT));
        assertThat(memberRepository.count(), is(Long.valueOf(CNT)));
    }

    @Test
    public void testQueryCreation() {
        assertThat(memberRepository.readBySeq(2).getSeq(), is(2));
        assertThat(memberRepository.queryBySeq(3).getSeq(), is(3));
        assertThat(memberRepository.findTop3ByNameLike("Lee%").size(), is(3));

        System.out.println(memberRepository.countByName("Lee1"));
        System.out.println(memberRepository.countByNameLike("Lee%"));
    }

    @Test
    public void testAsyncQueryCreation() throws InterruptedException, ExecutionException {
        CompletableFuture<List<Member>> completableFuture = memberRepository.findAllBy();
        while (completableFuture.isDone() == false) {
            System.out.println("waiting for the CompletableFuture to finish...");
            TimeUnit.MILLISECONDS.sleep(500);
        }

        List<Member> members = completableFuture.get();
        System.out.println(members);
    }

    @Test
    public void testNativeQuery() {
        List<Member> list = memberRepository.nativeQuery();
        list.forEach(System.out::println);

        System.out.println(memberRepository.nativeQueryBySeq(10));
    }

    @Test
    public void testPaging() {
        Page<Member> page = memberRepository.findAll(new PageRequest(3, 15));
        page.forEach(x -> System.out.println(x)); // It's called method reference
        System.out.println("page : " + page);
        System.out.println("totalElements : " + page.getTotalElements());
        System.out.println("totalPages : " + page.getTotalPages());
        System.out.println("nextPage : " + page.nextPageable());
        System.out.println("previousPage : " + page.previousPageable());
    }

    @Test
    public void testEntityManager() {
        Query query = em.createQuery("select p from Person p");
        List<Person> list = query.getResultList();
        assertThat(list.size(), is(CNT));
    }

    @Test(expected = ClassCastException.class)
    public void testOccursException() {
        Query query = em.createQuery("select p from Person p");
        List<Department> list = query.getResultList();
        assertThat(list.size(), is(CNT));
    }

    @Test
    public void testCompositTypeQuery() {
        TypedQuery typedQuery = em.createQuery("select p from Person p", Person.class);
        List<Person> list = typedQuery.getResultList();
        list.forEach(System.out::println);

        TypedQuery typedQuery1 = em.createQuery("select p.department from Person p", Department.class);
        List<Department> list1 = typedQuery1.getResultList();
        list1.forEach(System.out::println);
    }

    @Test
    public void testFilter() {
        TypedQuery typedQuery = em.createQuery("select p from Person p where p.department.id = :id and p.department.name = :name", Person.class);
        typedQuery.setParameter("id", 1L);
        typedQuery.setParameter("name", "development");

        List<Person> list = typedQuery.getResultList();
        assertThat(list.size(), is(1));
    }
}
