package com.kyu.boot.entity.twoway;

import lombok.Data;

import javax.persistence.*;

/**
 * @Project : test_project
 * @Date : 2017-05-12
 * @Author : nklee
 * @Description :
 */ // ==========================================
// N:1 관계
// 1:N 관계
// ==========================================
@Data
@Entity
public class Member4 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    /**
     * @JoinColumn 외래 키를 매핑할 때 사용. name 속성에는 외래 키 이름을 지정한다.
     * @JoinColumn 생략하면 외래 키를 찾을 때 기본 전략을 사용한다.
     * - 기본전략 : 필드명 + _ + 참조하는 테이블의 컬럼명 ex) departmentManyToOne_ID
     * optional 값을 false 로 설정하면 연관된 엔티티가 항상 있어야 한다.
     */
    @ManyToOne(optional = false, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "TEAM_ID")
    private Team4 team;

    public void setTeam(Team4 team) {
        if (this.team != null) {
            team.getMembers().remove(this); // 기존 팀과 관계를 제거
        }

        team.addMember(this);
        this.team = team;
    }
}
