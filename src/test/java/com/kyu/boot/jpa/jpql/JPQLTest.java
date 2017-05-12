package com.kyu.boot.jpa.jpql;

import com.kyu.boot.entity.jpql.Department;
import com.kyu.boot.entity.jpql.Person;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;


@Slf4j
@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest
public class JPQLTest {

    public static final int CNT = 200;

    @PersistenceContext
    private EntityManager entityManager;

    @Before
    public void beforeInsertData() {
        Department department = new Department();
        department.setName("development");
        entityManager.persist(department);

        for (int i = 0; i < CNT; i++) {
            Person person = new Person();
            person.setName("nklee" + i);
            person.setDepartment(department);
            entityManager.persist(person);
        }
    }

    @Test
    public void testJPQL() {
        // JPQL은 객체지향 쿼리
        entityManager.createQuery("SELECT p FROM Person p")
                .getResultList()
                .forEach(System.out::println);

        System.out.println("===================================================");
        entityManager.createQuery("SELECT p FROM Person p")
                .setMaxResults(5)
                .getResultList()
                .forEach(System.out::println);


        Query query = entityManager.createQuery("select p from Person p");
        List<Person> list = query.getResultList();
        assertThat(list.size(), is(CNT));
    }

    @Test
    public void testCompositTypeQuery() {
        TypedQuery typedQuery = entityManager.createQuery("select p from Person p", Person.class);
        List<Person> personList = typedQuery.getResultList();
        personList.forEach(System.out::println);

        TypedQuery typedQuery1 = entityManager.createQuery("select p.department from Person p", Department.class);
        List<Department> departmentList = typedQuery1.getResultList();
        departmentList.forEach(System.out::println);
    }

    @Test
    public void testFilter() {
        TypedQuery typedQuery = entityManager.createQuery("select p from Person p where p.department.name = :name", Person.class);
        typedQuery.setParameter("name", "development");

        List<Person> list = typedQuery.getResultList();
        assertThat(list.size(), is(1));
    }
}



