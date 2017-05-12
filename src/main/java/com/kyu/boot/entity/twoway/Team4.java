package com.kyu.boot.entity.twoway;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

/**
 * @Project : test_project
 * @Date : 2017-05-12
 * @Author : nklee
 * @Description :
 */
@Data
@Entity
public class Team4 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TEAM_ID")
    private long id;

    private String name;

    /**
     * 팀과 회원은 일대다 관계
     * mappedBy 속성은 양방향 매핑일 때 사용하는데 반대쪽 매핑의 필드 이름을 값으로 주면 된다.
     */
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "team")
    private Collection<Member4> members = new ArrayList<>();

    public void addMember(Member4 member) {
        this.members.add(member);
    }
}
