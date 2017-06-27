package com.kyu.boot.entity.oneway;

import javax.persistence.*;

/**
 * @Project : test_project
 * @Date : 2017-05-12
 * @Author : nklee
 * @Description :
 */ // ==========================================
// 1:N 관계
// ==========================================

@Entity
public class Member2 {

    @Id
    private int id;

    private String name;

    /**
     * @JoinColumn 외래 키를 매핑할 때 사용. name 속성에는 외래 키 이름을 지정한다.
     * @JoinColumn 생략하면 외래 키를 찾을 때 기본 전략을 사용한다.
     * - 기본전략 : 필드명 + _ + 참조하는 테이블의 컬럼명 ex) departmentManyToOne_ID
     * optional 값을 false 로 설정하면 연관된 엔티티가 항상 있어야 한다.
     */
    @ManyToOne(optional = true, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "TEAM_ID")
    private Team2 team;

    public Team2 getTeam() {
        return team;
    }

    public void setTeam(Team2 team) {
        this.team = team;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return "Member2{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", team=team" + team +
                '}';
    }
}
