package com.kyu.boot.jpa.springdatajpa;

import com.kyu.boot.constants.Gender;
import com.kyu.boot.entity.springdatajpa.Member3;
import com.kyu.boot.entity.springdatajpa.Phone3;
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

import javax.transaction.Transactional;
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
            Member3 member = new Member3("Lee" + i, Gender.MALE);
            member.addPhone(new Phone3("010-1111-" + i));
            member.addPhone(new Phone3("010-2222-" + i));
            memberRepository.save(member);
        }
    }

    @Test
    public void testTotalCnt() {
        List<Member3> list = memberRepository.findAll();
        assertThat(list.size(), is(CNT));
        assertThat(memberRepository.count(), is(Long.valueOf(CNT)));
    }

    @Test
    public void testSameInstance() {
        Member3 member1 = memberRepository.readByName("Lee0");
        Member3 member2 = memberRepository.readByName("Lee0");
        assertThat(member1, is(sameInstance(member2)));
    }

    @Test
    public void testLazyLoadingTest() {
        memberRepository.deleteAll();

        Member3 member = new Member3("lazyMember", Gender.MALE);
        member.addPhone(new Phone3("010-1111-1111"));
        member.addPhone(new Phone3("010-2222-2222"));
        member = memberRepository.save(member);

        /**
         * 위에서 save를 통해 영속성 컨텍스트에 데이터를 저장하였기에
         * FetchType.LAZY 으로 해도 핸드폰 값은 채워져 있는 상태로 반환된다.
         * 이유는 아래 readByName을 호출하게 되면 DB를 갖다 오는게 아닌 영속성 컨텍스트에 저장되어 있는 객체 정보를 가져오기 때문이다.
         */
        Member3 resultMember = memberRepository.readByName("lazyMember");

        // 영속성 컨텍스트에 저장되어 있는 객체를 가져오기 때문에 FetchType.LAZY 와는 무관하게 휴대폰 정보가 저장되어 있다.
        assertThat(member, is(sameInstance(resultMember)));
        assertThat(member.getPhone(), is(not(nullValue())));
    }

    @Test
    public void testEnumTest() {
        memberRepository.deleteAll();
        Member3 member = new Member3("Lee", Gender.MALE);
        memberRepository.save(member);
        assertThat(Gender.MALE, is(memberRepository.readByName("Lee").getGender()));

        Member3 member2 = new Member3("Lee2", Gender.FEMALE);
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
        CompletableFuture<List<Member3>> completableFuture = memberRepository.findAllBy();
        while (completableFuture.isDone() == false) {
            System.out.println("waiting for the CompletableFuture to finish...");
            TimeUnit.MILLISECONDS.sleep(500);
        }

        List<Member3> members = completableFuture.get();
        assertThat(members.size(), is(CNT));
    }

    @Test
    public void testNativeQuery() {
        List<Member3> list = memberRepository.nativeQuery();
        assertThat(list.size(), is(CNT));

        Member3 member = memberRepository.nativeQueryByName("Lee0");
        assertThat(member.getName(), is("Lee0"));
    }

    @Test
    public void testPaging() {
        Page<Member3> page = memberRepository.findAll(new PageRequest(3, 15));
        System.out.println("=======================================================");
        System.out.println("page : " + page);
        System.out.println("totalElements : " + page.getTotalElements());
        System.out.println("totalPages : " + page.getTotalPages());
        System.out.println("nextPage : " + page.nextPageable());
        System.out.println("previousPage : " + page.previousPageable());
        System.out.println("=======================================================");
    }
}

