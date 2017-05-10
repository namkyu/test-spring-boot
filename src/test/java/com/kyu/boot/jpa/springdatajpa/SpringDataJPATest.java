package com.kyu.boot.jpa.springdatajpa;

import com.kyu.boot.jpa.constants.Gender;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.scheduling.annotation.Async;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;


@Slf4j
@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringDataJPATest {

    public static final int CNT = 200;


    @Autowired
    private MemberRepository memberRepository;

    /**
     * 각각의 @Test메서드 실행 전 호출된다.
     *
     * @Test 메서드는 랜덤하게 실행된다. (순서 보장 없음)
     * 말하고 싶은 것은 엔티티의 @Id 값은 계속증가되니 이를참고해야 한다.
     * 이유는 entity manager를 각 @Test 메서드에서 공유하고 있기 때문이다.
     */
    @Before
    public void dummyInsertMemberData() {
        for (int i = 0; i < CNT; i++) {
            Member member = new Member("Lee" + i, Gender.MALE);
            member.addPhone(new Phone("010-1111-" + i));
            member.addPhone(new Phone("010-2222-" + i));
            memberRepository.save(member);
        }
    }

    @Test
    public void testTotalCnt() {
        List<Member> list = memberRepository.findAll();
        assertThat(list.size(), is(CNT));
        assertThat(memberRepository.count(), is(Long.valueOf(CNT)));
    }

    @Test
    public void testSameInstance() {
        Member member1 = memberRepository.readByName("Lee0");
        Member member2 = memberRepository.readByName("Lee0");
        assertThat(member1, is(sameInstance(member2)));
    }

    @Test
    public void testLazyLoadingTest() {
        memberRepository.deleteAll();

        Member member = new Member("lazyMember", Gender.MALE);
        member.addPhone(new Phone("010-1111-1111"));
        member.addPhone(new Phone("010-2222-2222"));
        member = memberRepository.save(member);

        /**
         * 위에서 save를 통해 영속성 컨텍스트에 데이터를 저장하였기에
         * FetchType.LAZY 으로 해도 핸드폰 값은 채워져 있는 상태로 반환된다.
         * 이유는 아래 readByName을 호출하게 되면 DB를 갖다 오는게 아닌 영속성 컨텍스트에 저장되어 있는 객체 정보를 가져오기 때문이다.
         */
        Member resultMember = memberRepository.readByName("lazyMember");

        // 영속성 컨텍스트에 저장되어 있는 객체를 가져오기 때문에 FetchType.LAZY 와는 무관하게 휴대폰 정보가 저장되어 있다.
        assertThat(member, is(sameInstance(resultMember)));
        assertThat(member.getPhone(), is(not(nullValue())));
    }

    @Test
    public void testEnumTest() {
        memberRepository.deleteAll();
        Member member = new Member("Lee", Gender.MALE);
        memberRepository.save(member);
        assertThat(Gender.MALE, is(memberRepository.readByName("Lee").getGender()));

        Member member2 = new Member("Lee2", Gender.FEMALE);
        memberRepository.save(member2);
        assertThat(Gender.FEMALE, is(memberRepository.readByName("Lee2").getGender()));
    }

    @Test
    public void testQueryCreation() {
        assertThat(memberRepository.findTop3ByNameLike("Lee%").size(), is(3));
        assertThat(memberRepository.countByNameLike("Lee%"), is(Long.valueOf(CNT)));
    }

    @Test
    public void testAsyncQueryCreation() throws InterruptedException, ExecutionException {
        CompletableFuture<List<Member>> completableFuture = memberRepository.findAllBy();
        while (completableFuture.isDone() == false) {
            System.out.println("waiting for the CompletableFuture to finish...");
            TimeUnit.MILLISECONDS.sleep(500);
        }

        List<Member> members = completableFuture.get();
        assertThat(members.size(), is(CNT));
    }

    @Test
    public void testNativeQuery() {
        List<Member> list = memberRepository.nativeQuery();
        assertThat(list.size(), is(CNT));

        Member member = memberRepository.nativeQueryByName("Lee0");
        assertThat(member.getName(), is("Lee0"));
    }

    @Test
    public void testPaging() {
        Page<Member> page = memberRepository.findAll(new PageRequest(3, 15));
        System.out.println("=======================================================");
        System.out.println("page : " + page);
        System.out.println("totalElements : " + page.getTotalElements());
        System.out.println("totalPages : " + page.getTotalPages());
        System.out.println("nextPage : " + page.nextPageable());
        System.out.println("previousPage : " + page.previousPageable());
        System.out.println("=======================================================");
    }
}

@Data
@Entity
class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int seq;

    private String name;

    @Enumerated(EnumType.ORDINAL)
    private Gender gender;

    // default fetch type = EAGER
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, targetEntity = Phone.class)
    private Collection<Phone> phone;

    public Member() {
    }

    public Member(String name) {
        this.name = name;
    }

    public Member(String name, Gender gender) {
        this.name = name;
        this.gender = gender;
    }

    public void addPhone(Phone p) {
        if (phone == null) {
            phone = new ArrayList<>();
        }

        phone.add(p);
    }
}

@Data
@Entity
class Phone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int seq;

    private String no;

    public Phone() {
    }

    public Phone(String no) {
        this.no = no;
    }

}

interface MemberRepository extends JpaRepository<Member, Integer> {

    Member findBySeqAndName(int seq, String name);

    List<Member> findTop3ByNameLike(String name);

    Member readBySeq(int seq);

    Member readByName(String name);

    Member queryBySeq(int seq);

    long countByNameLike(String name);

    long countByName(String name);

    @org.springframework.data.jpa.repository.Query(value = "SELECT seq, name FROM Member", nativeQuery = true)
    List<Member> nativeQuery();

    @org.springframework.data.jpa.repository.Query(value = "SELECT seq, name FROM Member WHERE name = :name", nativeQuery = true)
    Member nativeQueryByName(@Param(value = "name") String name);

    @Async
    CompletableFuture<List<Member>> findAllBy();
}