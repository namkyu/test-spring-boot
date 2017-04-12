package com.kyu.boot;

import com.kyu.boot.entity.onetomany.Member;
import com.kyu.boot.entity.onetomany.Phone;
import com.kyu.boot.entity.onetoone.Department;
import com.kyu.boot.entity.onetoone.Person;
import com.kyu.boot.repository.MemberRepository;
import com.kyu.boot.repository.PhoneRepository;
import com.kyu.boot.service.HelloService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestSpringBootApplicationTests {

	@Autowired
	private HelloService helloService;
	@PersistenceContext
	private EntityManager entityManager;
	@Autowired
	private MemberRepository memberRepository;
	@Autowired
	private PhoneRepository phoneRepository;

	@Test
	public void 트랜잭션테스트WithNPE() {
		assertThat(helloService.getUsers().size(), is(4));

		try {
			// save user
			helloService.getUserByOccurNPE();
		} catch (RuntimeException ex) {
			ex.printStackTrace();
		}

		helloService.getUsers().forEach(System.out::println);
		assertThat(helloService.getUsers().size(), is(4));
	}

	@Test
	public void 트랜잭션테스트WithNormal() {
		assertThat(helloService.getUsers().size(), is(4));
		// save user
		helloService.getUser();
		assertThat(helloService.getUsers().size(), is(5));
	}


	@Test
	@Transactional
	public void oneToOne() {

		for (int i = 0; i < 10; i++) {
			Person person = new Person();
			person.setName("nklee" + i);

			Department department = new Department();
			department.setName("development");
			person.setDepartment(department);

			// JPA 방식
			entityManager.persist(person);
			entityManager.persist(department);
		}

		// using JPQL (Java Persistent Query Language)
		entityManager.createQuery("SELECT p FROM Person p")
				.getResultList()
				.forEach(System.out::println);

		entityManager.createQuery("SELECT d FROM Department d WHERE d.id = :id")
				.setParameter("id", 1L)
				.setMaxResults(10)
				.getResultList()
				.forEach(System.out::println);
	}


	@Test
	@Transactional
	public void oneToMany() {
		Member member1 = new Member("Lee");
		member1.addPhone(new Phone("010-1111-1111"));
		member1.addPhone(new Phone("010-2222-2222"));

		Member member2 = new Member("Jang");
		member2.addPhone(new Phone("010-3333-3333"));

		Member member3 = new Member("JUN");

		// Spring Data JPA 방식
		memberRepository.save(member1);
		memberRepository.save(member2);
		memberRepository.save(member3);

		memberRepository.findAll().forEach(System.out::println);

		System.out.println("=====================================");
		System.out.println(memberRepository.findOne(2));
		System.out.println("=====================================");
		System.out.println(memberRepository.findBySeqAndName(1, "Lee"));
	}
}
